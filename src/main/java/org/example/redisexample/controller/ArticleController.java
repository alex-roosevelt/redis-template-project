package org.example.redisexample.controller;

import org.example.redisexample.model.ArticleInfo;
import org.example.redisexample.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/article")
public class ArticleController {

  @Autowired
  private ArticleService articleService;

  @GetMapping("/{id}")
  public ResponseEntity<ArticleInfo> getById(@PathVariable Long id) {
    return ResponseEntity.ok(articleService.getCachedArticle(id));
  }

  @GetMapping("/trending")
  public ResponseEntity<ArticleInfo> getTrendArticle(){
    return ResponseEntity.ok(articleService.getRandomArticle());
  }
}
