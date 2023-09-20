package ru.lazarev.springcourse.dao;

import ru.lazarev.springcourse.domain.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreDao {

    List<Genre> findAll();

    Optional<Genre> findById(Long id);
}
