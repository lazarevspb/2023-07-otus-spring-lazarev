package ru.lazarev.springcourse.library.repository;

import org.springframework.data.repository.CrudRepository;
import ru.lazarev.springcourse.library.domain.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreRepository extends CrudRepository<Genre, Long> {
    List<Genre> findAll();

    Optional<Genre> findByName(String name);
}
