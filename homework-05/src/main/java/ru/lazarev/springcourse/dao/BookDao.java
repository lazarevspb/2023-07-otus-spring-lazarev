package ru.lazarev.springcourse.dao;

import ru.lazarev.springcourse.domain.Book;
import ru.lazarev.springcourse.dto.BookDto;

import java.util.List;

public interface BookDao {

    List<BookDto> findAll();

    BookDto findById(Long id);

    void save(Book book);

    void update(Book book);

    void delete(Long id);
}
