package org.example.redisexample.repository;

import java.util.List;
import org.example.redisexample.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

  List<Comment> findAllByArticleId(Long articleId);
}
