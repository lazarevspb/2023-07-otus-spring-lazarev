package ru.lazarev.springcourse.service;

import ru.lazarev.springcourse.domain.Comment;
import ru.lazarev.springcourse.dto.CommentDto;

import java.util.List;

public interface CommentService {
    List<Comment> findAllCommentsByBookId(Long id);

    Comment findCommentById(Long id);

    void saveComment(CommentDto comment);

    void updateComment(CommentDto comment);

    void deleteCommentById(Long id);
}
