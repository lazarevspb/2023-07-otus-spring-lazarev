package ru.lazarev.springcourse.library.repository;

import org.springframework.data.repository.CrudRepository;
import ru.lazarev.springcourse.library.domain.Comment;


public interface CommentRepository extends CrudRepository<Comment, Long> {}
