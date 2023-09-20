package ru.lazarev.springcourse.service.impl;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.lazarev.springcourse.dao.BookDao;
import ru.lazarev.springcourse.domain.Author;
import ru.lazarev.springcourse.domain.Book;
import ru.lazarev.springcourse.domain.Comment;
import ru.lazarev.springcourse.domain.Genre;
import ru.lazarev.springcourse.dto.BookDto;
import ru.lazarev.springcourse.mapper.AuthorMapperImpl;
import ru.lazarev.springcourse.mapper.GenreMapperImpl;
import ru.lazarev.springcourse.service.AuthorService;
import ru.lazarev.springcourse.service.BookService;
import ru.lazarev.springcourse.service.GenreService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith({SpringExtension.class})
@ContextConfiguration(classes = {BookServiceImpl.class, AuthorMapperImpl.class, GenreMapperImpl.class})
@FieldDefaults(level = AccessLevel.PRIVATE)
class BookServiceImplTest {

    public static final String Book_NAME = "Book_name";

    public static final long Book_ID = 1L;

    public static final long AUTHOR_ID = 1L;

    public static final String AUTHOR_NAME = "Author_name";

    public static final long GENRE_ID = 2L;

    public static final String GENRE_NAME = "Genre_name";

    @MockBean
    BookDao bookDao;

    @MockBean
    AuthorService authorService;

    @MockBean
    GenreService genreService;


    @Autowired
    BookService service;

    @Test
    void saveBook() {
        when(authorService.findAuthorById(any())).thenReturn(getAuthor());
        when(genreService.findGenreById(any())).thenReturn(getGenre());

        service.saveBook(getBookDto());

        var savedBook = getBook();
        savedBook.setId(null);
        verify(bookDao, times(1)).save(eq((savedBook)));
    }

    @Test
    void updateBook() {
        when(authorService.findAuthorById(any())).thenReturn(getAuthor());
        when(genreService.findGenreById(any())).thenReturn(getGenre());

        service.updateBook(getBookDto());

        verify(bookDao, times(1)).save(eq(getBook()));
    }

    @Test
    void deleteBookById() {
        service.deleteBookById(Book_ID);

//        verify(bookDao, times(1)).delete(book);
    }

    @Test
    void getAllBook() {
        when(bookDao.findAll()).thenReturn(getBookList());

        var actual = service.findAllBooks();

        assertEquals(getBookList(), actual);
    }

    @Test
    void findBookById() {
        when(bookDao.findById(eq(AUTHOR_ID))).thenReturn(getBook());

        var actual = service.findBookById(AUTHOR_ID);

        assertEquals(getBook(), actual);
    }

    private List<Book> getBookList() {
        return List.of(getBook());
    }

    private Book getBook() {
        return new Book(Book_ID, Book_NAME, getAuthor(), getGenre(), List.of(new Comment()));
    }



    private BookDto getBookDto() {
        return new BookDto(Book_ID, Book_NAME, AUTHOR_ID, GENRE_ID);
    }

    private Genre getGenre() {
        return new Genre(GENRE_ID, GENRE_NAME);
    }

    private Author getAuthor() {
        return new Author(AUTHOR_ID, AUTHOR_NAME);
    }
}