package ru.lazarev.springcourse.service.impl;

import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import ru.lazarev.springcourse.dao.BookDao;
import ru.lazarev.springcourse.domain.Book;
import ru.lazarev.springcourse.dto.BookDto;
import ru.lazarev.springcourse.service.AuthorService;
import ru.lazarev.springcourse.service.BookService;
import ru.lazarev.springcourse.service.GenreService;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookServiceImpl implements BookService {

    BookDao bookDao;

    AuthorService authorService;

    GenreService genreService;

    @Override
    public List<Book> findAllBooks() {
        return bookDao.findAll();
    }

    @Override
    public Book findBookById(Long id) {
        return bookDao.findById(id);
    }

    @Override
    @Transactional
    public void saveBook(BookDto book) {
        bookDao.save(new Book(null, book.getTitle(), authorService.findAuthorById(book.getAuthorId()),
                              genreService.findGenreById(book.getGenreId())));
    }

    @Override
    @Transactional
    public void updateBook(BookDto book) {
        bookDao.save(new Book(book.getId(), book.getTitle(), authorService.findAuthorById(book.getAuthorId()),
                              genreService.findGenreById(book.getGenreId())));
    }

    @Override
    @Transactional
    public void deleteBookById(Long id) {
        bookDao.delete(id);
    }
}
