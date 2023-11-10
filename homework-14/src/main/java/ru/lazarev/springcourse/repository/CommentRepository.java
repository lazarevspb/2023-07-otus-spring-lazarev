package ru.lazarev.springcourse.repository;

import org.springframework.data.repository.CrudRepository;
import ru.lazarev.springcourse.postgres.Comment;


public interface CommentRepository extends CrudRepository<Comment, Long> {}
