package ru.lazarev.springcourse.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.lazarev.springcourse.mongo.MongoBook;

public interface bookCollectionRepository extends MongoRepository<MongoBook, String> {
}
