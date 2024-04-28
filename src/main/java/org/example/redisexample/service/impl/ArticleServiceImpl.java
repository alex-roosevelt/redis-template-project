package org.example.redisexample.service.impl;

import static redis.clients.jedis.Protocol.Command.TTL;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Random;
import org.example.redisexample.model.ArticleInfo;
import org.example.redisexample.model.Comment;
import org.example.redisexample.repository.ArticleRepository;
import org.example.redisexample.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Service
public class ArticleServiceImpl implements ArticleService {

  @Autowired
  private ArticleRepository articleRepository;

  @Autowired
  private JedisPool jedis;

  @Autowired
  private ObjectMapper mapper;

  private ArticleInfo getArticle(Long id) {
    return articleRepository.findById(id)
        .map(article -> new ArticleInfo(
            article.getId(),
            article.getTitle(),
            article.getText(),
            article.getComments().stream().mapToInt(Comment::getScore)
                .average().orElse(0)

        )).orElse(null);
  }

  @Override
  public ArticleInfo getRandomArticle() {
    long count = articleRepository.count();
    long articleNum = new Random().nextLong(1, count);
    return getCachedArticle(articleNum);
  }

  @Override
  public ArticleInfo getCachedArticle(Long id) {
    try (Jedis redis = jedis.getResource()) {
      String key = "article:%d".formatted(id);
      String raw = redis.get(key);
      if (raw != null) {
        return mapper.readValue(raw, ArticleInfo.class);
      }
      ArticleInfo article = getArticle(id);
      if (article == null) {
        return null;
      }
      redis.setex(key.getBytes(), TTL.ordinal(), mapper.writeValueAsString(article).getBytes());
      return article;
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }
}
