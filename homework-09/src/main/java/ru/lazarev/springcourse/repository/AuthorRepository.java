package ru.lazarev.springcourse.repository;

import org.springframework.data.repository.CrudRepository;
import ru.lazarev.springcourse.domain.Author;

import java.util.List;


public interface AuthorRepository extends CrudRepository<Author, Long> {

    List<Author> findAll();
}
