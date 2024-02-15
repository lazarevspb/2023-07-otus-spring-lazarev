package ru.lazarev.springcourse.library.service;

import ru.lazarev.springcourse.library.domain.Comment;
import ru.lazarev.springcourse.library.dto.CommentDto;

import java.util.List;

public interface CommentService {

    Comment findCommentById(Long id);

    List<CommentDto> findCommentsByBookId(Long id);

    void saveComment(CommentDto comment);

    void updateComment(CommentDto comment);

    void deleteCommentById(Long id);
}
