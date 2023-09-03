package ru.lazarev.springcourse.service;

import ru.lazarev.springcourse.domain.Book;
import ru.lazarev.springcourse.dto.BookDto;

import java.util.List;

public interface BookService {

    List<BookDto> findAllBooks();

    BookDto findBookById(Long id);

    void saveBook(Book book);

    void updateBook(Book book);

    void deleteBookById(Long id);
}
