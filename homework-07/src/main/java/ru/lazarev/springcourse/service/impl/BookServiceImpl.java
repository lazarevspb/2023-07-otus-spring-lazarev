package ru.lazarev.springcourse.service.impl;

import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import ru.lazarev.springcourse.repository.BookRepository;
import ru.lazarev.springcourse.domain.Book;
import ru.lazarev.springcourse.domain.Comment;
import ru.lazarev.springcourse.dto.BookDto;
import ru.lazarev.springcourse.service.AuthorService;
import ru.lazarev.springcourse.service.BookService;
import ru.lazarev.springcourse.service.GenreService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookServiceImpl implements BookService {

    BookRepository repository;

    AuthorService authorService;

    GenreService genreService;

    @Override
    @Transactional
    public String findAllBooks() {
        return repository.findAll().stream()
            .map(Book::toString).collect(Collectors.joining(",", "[", "]"));
    }

    @Override
    public List<Comment> findAllCommentByBookId(Long id) {
        return repository.findById(id).get().getComments().stream()
            .toList();
    }

    @Override
    public Book findBookById(Long id) {
        return repository.findById(id).get();
    }

    @Override
    public void saveBook(BookDto book) {
        repository.save(new Book(null, book.getTitle(), authorService.findAuthorById(book.getAuthorId()),
                                 genreService.findGenreById(book.getGenreId()), List.of(new Comment())));

    }

    @Override
    public void updateBook(BookDto book) {
        repository.save(new Book(book.getId(), book.getTitle(), authorService.findAuthorById(book.getAuthorId()),
                                 genreService.findGenreById(book.getGenreId()), List.of(new Comment())));
    }

    @Override
    public void deleteBookById(Long id) {
        repository.findById(id)
            .ifPresent(repository::delete);
    }
}
