package ru.lazarev.springcourse.library.repository;

import org.springframework.data.repository.CrudRepository;
import ru.lazarev.springcourse.library.domain.Author;

import java.util.List;
import java.util.Optional;


public interface AuthorRepository extends CrudRepository<Author, Long> {

    List<Author> findAll();

    Optional<Author> findByName(String name);
}
