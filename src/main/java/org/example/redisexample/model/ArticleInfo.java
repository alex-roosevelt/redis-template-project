package org.example.redisexample.model;


public record ArticleInfo (
  Long id,
  String title,
  String text,
  Double rating
) {}
