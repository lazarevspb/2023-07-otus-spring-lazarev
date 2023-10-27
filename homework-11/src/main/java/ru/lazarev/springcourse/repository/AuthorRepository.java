package ru.lazarev.springcourse.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.lazarev.springcourse.domain.Author;

@Repository
public interface AuthorRepository extends ReactiveCrudRepository<Author, Long> {

    Flux<Author> findAll();

    Mono<Author> findByName(String name);
}
