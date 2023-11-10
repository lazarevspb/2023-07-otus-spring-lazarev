package ru.lazarev.springcourse.service;

import ru.lazarev.springcourse.postgres.PostgresBook;
import ru.lazarev.springcourse.postgres.Comment;

import java.util.List;

public interface BookService {

    List<PostgresBook> findAllBooks();

    List<Comment> findAllCommentByBookId(Long id);

    PostgresBook findBookById(Long id);


}
