package ru.lazarev.springcourse.repository;

import org.springframework.data.repository.CrudRepository;
import ru.lazarev.springcourse.domain.Comment;


public interface CommentRepository extends CrudRepository<Comment, Long> {}
