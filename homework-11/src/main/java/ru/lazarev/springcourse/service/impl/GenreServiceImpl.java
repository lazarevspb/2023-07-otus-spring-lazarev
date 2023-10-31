package ru.lazarev.springcourse.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import ru.lazarev.springcourse.domain.Genre;
import ru.lazarev.springcourse.repository.GenreRepository;
import ru.lazarev.springcourse.service.GenreService;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class GenreServiceImpl implements GenreService {

    GenreRepository repository;

    @Override
    public Mono<Genre> findGenreById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Mono<Genre> findByName(String name) {
        return repository.findByName(name);
    }
}
