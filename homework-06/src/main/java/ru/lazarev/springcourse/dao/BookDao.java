package ru.lazarev.springcourse.dao;

import ru.lazarev.springcourse.domain.Book;

import java.util.List;

public interface BookDao {

    List<Book> findAll();

    Book findById(Long id);

    void save(Book book);

    void update(Book book);

    void delete(Long id);
}
