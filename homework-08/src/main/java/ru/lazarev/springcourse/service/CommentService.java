package ru.lazarev.springcourse.service;

import ru.lazarev.springcourse.domain.Comment;
import ru.lazarev.springcourse.dto.CommentDto;

public interface CommentService {

    Comment findCommentById(String id);

    void saveComment(CommentDto comment);

    void updateComment(CommentDto comment);

    void deleteCommentById(String id);
}
