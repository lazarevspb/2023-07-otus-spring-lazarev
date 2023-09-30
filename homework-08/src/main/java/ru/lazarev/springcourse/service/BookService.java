package ru.lazarev.springcourse.service;

import ru.lazarev.springcourse.domain.Book;
import ru.lazarev.springcourse.domain.Comment;
import ru.lazarev.springcourse.dto.BookDto;

import java.util.List;

public interface BookService {

    String findAllBooks();

    List<Comment> findAllCommentByBookId(String id);

    Book findBookById(String id);

    void saveBook(BookDto book);

    void updateBook(BookDto book);

    void deleteBookById(String id);
}
