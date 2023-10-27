package ru.lazarev.springcourse.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;
import ru.lazarev.springcourse.domain.Genre;

public interface GenreRepository extends ReactiveCrudRepository<Genre, Long> {

    Mono<Genre> findByName(String name);
}
