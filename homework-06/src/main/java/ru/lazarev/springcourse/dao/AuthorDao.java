package ru.lazarev.springcourse.dao;

import ru.lazarev.springcourse.domain.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorDao {

    List<Author> findAll();

    Optional<Author> findById(Long id);
}
