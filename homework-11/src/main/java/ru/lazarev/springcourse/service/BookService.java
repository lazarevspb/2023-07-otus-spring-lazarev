package ru.lazarev.springcourse.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.lazarev.springcourse.dto.BookDto;

public interface BookService {

    Flux<BookDto> findAllBooks();

    Mono<BookDto> findBookById(Long id);

    Mono<BookDto> save(BookDto book);

    Mono<Void> deleteBookById(Long id);

}
