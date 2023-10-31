package ru.lazarev.springcourse.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import ru.lazarev.springcourse.domain.Book;

@Repository
public interface BookRepository extends ReactiveCrudRepository<Book, Long> {
}
