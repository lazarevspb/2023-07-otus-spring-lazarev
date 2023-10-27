package ru.lazarev.springcourse.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import ru.lazarev.springcourse.domain.Book;
import ru.lazarev.springcourse.dto.BookDto;
import ru.lazarev.springcourse.repository.BookRepository;
import ru.lazarev.springcourse.service.AuthorService;
import ru.lazarev.springcourse.service.BookService;
import ru.lazarev.springcourse.service.GenreService;

import java.util.Comparator;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookServiceImpl implements BookService {

    BookRepository repository;

    AuthorService authorService;

    GenreService genreService;

    @Override
    public Flux<BookDto> findAllBooks() {
        return repository.findAll()
            .publishOn(Schedulers.boundedElastic())
            .flatMap(this::getBookDto)
                             .sort(Comparator.comparing(BookDto::getId));
    }

    @Override
    public Mono<BookDto> findBookById(Long id) {
        return repository.findById(id).flatMap(this::getBookDto);
    }

    private Mono<BookDto> getBookDto(Book book) {
        return Mono.zip(
                authorService.findAuthorById(book.getAuthorId()),
                genreService.findGenreById(book.getGenreId())
            )
            .map(tuple ->
                     BookDto.builder()
                         .id(book.getId())
                         .author(tuple.getT1().getName())
                         .genre(tuple.getT2().getName())
                         .title(book.getTitle()).build());
    }

    @Override
    public Mono<Void> deleteBookById(Long id) {
       return repository.deleteById(id);
    }

    @Override
    public Mono<BookDto> save(BookDto newBook) {
      return Mono.just(newBook)
          .publishOn(Schedulers.boundedElastic())
          .flatMap(this::getBook)
          .flatMap(repository::save)
          .flatMap(this::getBookDto);

    }

    private Mono<Book> getBook(BookDto bookDto) {
        return Mono.zip(
                authorService.findByName(bookDto.getAuthor()),
                genreService.findByName(bookDto.getGenre())
            )
            .map(tuple ->
                     Book.builder()
                         .id(bookDto.getId())
                         .authorId(tuple.getT1().getId())
                         .genreId(tuple.getT2().getId())
                         .title(bookDto.getTitle()).build());
    }
}
