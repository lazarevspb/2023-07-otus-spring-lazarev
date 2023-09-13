package ru.lazarev.springcourse.dao;

import ru.lazarev.springcourse.domain.Comment;
import ru.lazarev.springcourse.dto.CommentDto;

public interface CommentDao {

    Comment findById(Long id);

    void save(CommentDto comment);

    void update(CommentDto comment);

    void delete(Long id);
}
