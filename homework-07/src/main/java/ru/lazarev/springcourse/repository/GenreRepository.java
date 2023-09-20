package ru.lazarev.springcourse.repository;

import org.springframework.data.repository.CrudRepository;
import ru.lazarev.springcourse.domain.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreRepository extends CrudRepository<Genre, Long> {
    List<Genre> findAll();

    Optional<Genre> findById(Long id);
}
