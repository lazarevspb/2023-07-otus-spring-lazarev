package ru.lazarev.springcourse.service.impl;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.lazarev.springcourse.domain.Author;
import ru.lazarev.springcourse.domain.Book;
import ru.lazarev.springcourse.domain.Comment;
import ru.lazarev.springcourse.domain.Genre;
import ru.lazarev.springcourse.dto.BookDto;
import ru.lazarev.springcourse.repository.BookRepository;
import ru.lazarev.springcourse.service.AuthorService;
import ru.lazarev.springcourse.service.BookService;
import ru.lazarev.springcourse.service.GenreService;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class BookServiceImpl implements BookService {

    BookRepository repository;

    AuthorService authorService;

    GenreService genreService;

    @Override
    @Transactional
    @CircuitBreaker(name = "myCircuitBreaker", fallbackMethod = "findAllErrorBooks")
    public List<Book> findAllBooks() {
        var bookList = repository.findAll();
        bookList.sort(Comparator.comparing(Book::getId));
        return bookList;
    }

    @Override
    @CircuitBreaker(name = "myCircuitBreaker", fallbackMethod = "findAllErrorComments")
    public List<Comment> findAllCommentByBookId(Long id) {
        return repository.findById(id).get().getComments().stream()
            .collect(Collectors.toList());
    }

    @Override
    @CircuitBreaker(name = "myCircuitBreaker", fallbackMethod = "getErrorBook")
    public Book findBookById(Long id) {
        return repository.findById(id).get();
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void saveBook(BookDto newBook) {
        if (Objects.nonNull(newBook.getId())) {
            var oldBook = repository.findById(newBook.getId());
            repository.save(new Book(newBook.getId(), newBook.getTitle(), getAuthor(newBook),
                                     getGenre(newBook),
                                     getOldComments(oldBook)));
        } else {
            repository.save(new Book(null, newBook.getTitle(), getAuthor(newBook),
                                     getGenre(newBook),
                                     null));
        }
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void updateBook(BookDto newBook) {
        saveBook(newBook);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteBookById(Long id) {
        repository.findById(id)
            .ifPresent(repository::delete);
    }

    private Book getEmptyBook() {
        return new Book(0L, "UNAVAILABLE", new Author(0L, "UNAVAILABLE"), new Genre(0L, "UNAVAILABLE"),
                        Collections.emptyList());
    }

    public Book getErrorBook(Throwable throwable) {
        log.error(throwable.getMessage());
        return getEmptyBook();
    }

    public List<Book> findAllErrorBooks(Throwable throwable) {
        log.error(throwable.getMessage());
        return Collections.singletonList(getEmptyBook());
    }

    public List<Book> findAllErrorComments(Throwable throwable) {
        log.error(throwable.getMessage());
        return Collections.emptyList();
    }

    private Author getAuthor(BookDto newBook) {
        return authorService.findByName(newBook.getAuthor());
    }

    private Genre getGenre(BookDto newBook) {
        return genreService.findByName(newBook.getGenre());
    }

    private List<Comment> getOldComments(Optional<Book> oldBook) {
        return oldBook.map(Book::getComments).orElse(null);
    }

}
