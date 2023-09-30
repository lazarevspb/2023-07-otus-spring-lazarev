package ru.lazarev.springcourse.repository;

import org.springframework.data.repository.CrudRepository;
import ru.lazarev.springcourse.domain.Book;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, Long> {

    List<Book> findAll();
}
