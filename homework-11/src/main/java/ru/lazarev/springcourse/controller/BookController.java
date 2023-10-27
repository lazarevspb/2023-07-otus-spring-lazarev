package ru.lazarev.springcourse.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.lazarev.springcourse.dto.BookDto;
import ru.lazarev.springcourse.service.BookService;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Slf4j
@RequestMapping("api/v1/books")
public class BookController {
    BookService bookService;

    @GetMapping("")
    public Flux<BookDto> getAllBooks() {
        return bookService.findAllBooks();
    }

    @GetMapping("{id}")
    public Mono<BookDto> getBookById(@PathVariable Long id) {
        return bookService.findBookById(id);

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<ResponseEntity<Void>> deleteBook(@PathVariable Long id) {
        return bookService.findBookById(id)
            .flatMap(existingBook -> bookService.deleteBookById(existingBook.getId())
                .then(Mono.just(new ResponseEntity<Void>(HttpStatus.NO_CONTENT))))
            .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping
    public Mono<ResponseEntity<Void>> updateBook(@RequestBody BookDto book) {
            return bookService.findBookById(book.getId())
            .flatMap(bookDto -> bookService.save(book)
                .then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK))))
            .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public Mono<BookDto> saveBook(@RequestBody BookDto book) {
        return bookService.save(book);
    }
}
