package ru.lazarev.springcourse.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.lazarev.springcourse.domain.Author;

import java.util.List;

public interface AuthorRepository extends MongoRepository<Author, String> {
}
