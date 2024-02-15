package ru.lazarev.springcourse.library.repository;

import org.springframework.data.repository.CrudRepository;
import ru.lazarev.springcourse.library.domain.Book;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, Long> {

    List<Book> findAll();
}
