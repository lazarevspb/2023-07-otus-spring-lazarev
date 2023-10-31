package ru.lazarev.springcourse.service;

import reactor.core.publisher.Mono;
import ru.lazarev.springcourse.domain.Author;

public interface AuthorService {

    Mono<Author> findAuthorById(Long id);

    Mono<Author> findByName(String name);
}
