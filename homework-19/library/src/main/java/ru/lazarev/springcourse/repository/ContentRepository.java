package ru.lazarev.springcourse.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.lazarev.springcourse.domain.Content;

public interface ContentRepository extends MongoRepository<Content, String> {
    Content findContentByBookId(Long bookId);
}
