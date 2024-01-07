package ru.lazarev.springcourse.library.service;

import ru.lazarev.springcourse.library.domain.Comment;
import ru.lazarev.springcourse.library.dto.CommentDto;

public interface CommentService {

    Comment findCommentById(Long id);

    void saveComment(CommentDto comment);

    void updateComment(CommentDto comment);

    void deleteCommentById(Long id);
}
