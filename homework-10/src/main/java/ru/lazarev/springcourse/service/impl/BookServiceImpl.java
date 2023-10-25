package ru.lazarev.springcourse.service.impl;

import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import ru.lazarev.springcourse.domain.Author;
import ru.lazarev.springcourse.domain.Genre;
import ru.lazarev.springcourse.repository.BookRepository;
import ru.lazarev.springcourse.domain.Book;
import ru.lazarev.springcourse.domain.Comment;
import ru.lazarev.springcourse.dto.BookDto;
import ru.lazarev.springcourse.service.AuthorService;
import ru.lazarev.springcourse.service.BookService;
import ru.lazarev.springcourse.service.GenreService;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookServiceImpl implements BookService {

    BookRepository repository;

    AuthorService authorService;

    GenreService genreService;

    @Override
    @Transactional
    public List<Book> findAllBooks() {
        var bookList = repository.findAll();
        bookList.sort(Comparator.comparing(Book::getId));
        return bookList;
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
    public Book saveBook(BookDto newBook) {
        if (Objects.nonNull(newBook.getId())) {
            var oldBook = repository.findById(newBook.getId());
            return repository.save(new Book(newBook.getId(), newBook.getTitle(), getAuthor(newBook),
                                            getGenre(newBook),
                                            getOldComments(oldBook)));
        } else {
            return repository.save(new Book(null, newBook.getTitle(), getAuthor(newBook),
                                            getGenre(newBook),
                                            null));
        }
    }

    @Override
    public void updateBook(BookDto newBook) {
        saveBook(newBook);
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

    @Override
    public void deleteBookById(Long id) {
        repository.findById(id)
            .ifPresent(repository::delete);
    }
}
