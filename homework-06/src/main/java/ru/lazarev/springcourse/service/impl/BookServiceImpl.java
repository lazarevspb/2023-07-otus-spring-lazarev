package ru.lazarev.springcourse.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import ru.lazarev.springcourse.dao.BookDao;
import ru.lazarev.springcourse.domain.Book;
import ru.lazarev.springcourse.dto.AuthorDto;
import ru.lazarev.springcourse.dto.BookDto;
import ru.lazarev.springcourse.dto.GenreDto;
import ru.lazarev.springcourse.mapper.BookMapper;
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

    BookMapper bookMapper;

    @Override
    public List<BookDto> findAllBooks() {
        return bookDao.findAll().stream()
            .map(this::mapToDto)
            .toList();
    }

    @Override
    public BookDto findBookById(Long id) {
        return mapToDto(bookDao.findById(id));
    }

    @Override
    public void saveBook(Book book) {
        bookDao.save(book);
    }

    @Override
    public void updateBook(Book book) {
        bookDao.update(book);
    }

    @Override
    public void deleteBookById(Long id) {
        bookDao.delete(id);
    }

    private BookDto mapToDto(Book book) {
        return bookMapper.map(book, getAuthorById(book), getGenreById(book));
    }

    private GenreDto getGenreById(Book book) {
        return genreService.findGenreById(book.getGenreId());
    }

    private AuthorDto getAuthorById(Book book) {
        return authorService.findAuthorById(book.getAuthorId());
    }
}
