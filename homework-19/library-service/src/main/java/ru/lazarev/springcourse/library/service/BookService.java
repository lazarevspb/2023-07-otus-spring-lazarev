package ru.lazarev.springcourse.library.service;

import ru.lazarev.springcourse.library.domain.Book;
import ru.lazarev.springcourse.library.domain.Comment;
import ru.lazarev.springcourse.library.dto.BookDto;

import java.util.List;

public interface BookService {

    List<Book> findAllBooks();

    List<Comment> findAllCommentByBookId(Long id);

    Book findBookById(Long id);

    Book saveBook(BookDto book);

    void updateBook(BookDto book);

    void deleteBookById(Long id);
}
