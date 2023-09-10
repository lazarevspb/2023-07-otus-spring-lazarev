package ru.lazarev.springcourse.dao;

import ru.lazarev.springcourse.domain.Comment;
import ru.lazarev.springcourse.dto.CommentDto;

import java.util.List;

public interface CommentDao {
    List<Comment> findAllCommentByBookId(Long id);

    Comment findById(Long id);

    void save(CommentDto comment);

    void update(CommentDto comment);

    void delete(Long id);
}
