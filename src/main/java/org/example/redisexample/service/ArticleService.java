package org.example.redisexample.service;

import org.example.redisexample.model.ArticleInfo;

public interface ArticleService {

  ArticleInfo getCachedArticle(Long id);
  ArticleInfo getRandomArticle();
}
