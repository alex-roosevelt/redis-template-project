package org.example.redisexample.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "comments")
@RequiredArgsConstructor
public class Comment {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String text;
  private Integer score;

  @ManyToOne
  @JoinColumn(name = "article_id")
  private Article article;
}
