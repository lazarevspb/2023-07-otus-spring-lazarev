package ru.lazarev.springcourse.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.lazarev.springcourse.domain.Genre;

import java.util.List;

public interface GenreRepository extends MongoRepository<Genre, String> {
}
