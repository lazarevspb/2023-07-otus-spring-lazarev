package ru.lazarev.springcourse.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.lazarev.springcourse.domain.Book;

import java.util.List;

public interface BookRepository extends MongoRepository<Book, String> {
}
