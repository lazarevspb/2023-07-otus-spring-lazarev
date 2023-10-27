package ru.lazarev.springcourse.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import ru.lazarev.springcourse.domain.Comment;

@Repository
public interface CommentRepository extends ReactiveCrudRepository<Comment, Long> {}
