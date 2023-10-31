package ru.lazarev.springcourse.service;

import reactor.core.publisher.Mono;
import ru.lazarev.springcourse.domain.Genre;

public interface GenreService {

    Mono<Genre> findGenreById(Long id);

    Mono<Genre> findByName(String name);
}
