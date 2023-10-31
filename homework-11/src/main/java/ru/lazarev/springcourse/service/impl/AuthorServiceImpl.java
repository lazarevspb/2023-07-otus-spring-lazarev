package ru.lazarev.springcourse.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import ru.lazarev.springcourse.domain.Author;
import ru.lazarev.springcourse.repository.AuthorRepository;
import ru.lazarev.springcourse.service.AuthorService;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthorServiceImpl implements AuthorService {

    AuthorRepository repository;

    @Override
    public Mono<Author> findAuthorById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Mono<Author> findByName(String name) {
        return repository.findByName(name);
    }
}
