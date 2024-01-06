package ru.lazarev.springcourse.repository;

import org.springframework.data.repository.CrudRepository;
import ru.lazarev.springcourse.domain.Author;

import java.util.List;
import java.util.Optional;


public interface AuthorRepository extends CrudRepository<Author, Long> {

    List<Author> findAll();

    Optional<Author> findByName(String name);
}
