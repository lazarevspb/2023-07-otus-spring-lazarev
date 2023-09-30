package ru.lazarev.springcourse.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.lazarev.springcourse.domain.Comment;

public interface CommentRepository extends MongoRepository<Comment, String> {}
