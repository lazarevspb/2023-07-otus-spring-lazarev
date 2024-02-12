package ru.lazarev.springcourse.library.repository;

import org.springframework.data.repository.CrudRepository;
import ru.lazarev.springcourse.library.domain.Comment;

import java.util.List;

public interface CommentRepository extends CrudRepository<Comment, Long> {
    List<Comment> findCommentsByBookId(Long bookId);
}
