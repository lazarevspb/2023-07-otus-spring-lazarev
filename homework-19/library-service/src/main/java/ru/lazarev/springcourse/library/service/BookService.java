package ru.lazarev.springcourse.library.service;

import ru.lazarev.springcourse.library.domain.Book;
import ru.lazarev.springcourse.library.domain.Comment;
import ru.lazarev.springcourse.library.dto.BookDto;

import java.util.List;

public interface BookService {

    List<Book> findAllBooks(Long userId);

    List<Comment> findAllCommentByBookId(Long bookId, Long userId);

    Book findBookById(Long bookId, Long userId);

    Book saveBook(BookDto book, Long userId);

    void updateBook(BookDto book, Long userId);

    void deleteBookById(Long bookId, Long userId);
}
