package ru.lazarev.springcourse.service.impl;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.lazarev.springcourse.domain.Author;
import ru.lazarev.springcourse.domain.Book;
import ru.lazarev.springcourse.domain.Comment;
import ru.lazarev.springcourse.domain.Genre;
import ru.lazarev.springcourse.dto.BookDto;
import ru.lazarev.springcourse.mapper.AuthorMapperImpl;
import ru.lazarev.springcourse.mapper.GenreMapperImpl;
import ru.lazarev.springcourse.repository.BookRepository;
import ru.lazarev.springcourse.service.AuthorService;
import ru.lazarev.springcourse.service.BookService;
import ru.lazarev.springcourse.service.GenreService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public static final String AUTHOR_1 = "Author 1";

    public static final String GENRE_2 = "Genre 2";

    public static final String AUTHOR_NAME = "Author_name";

    public static final String GENRE_NAME = "Genre_name";

    public static final String EXPECTED_LIST_STRING = "[Book(id=1, title=Book_name, author=Author(id=1, "
        + "name=Author_name), genre=Genre(id=2, name=Genre_name), comments=[Comment(id=null, text=null)])]";
    public static final Long GENRE_ID = 2L;

    public static final Long AUTHOR_ID = 1L;

    @MockBean
    BookRepository repository;

    @MockBean
    AuthorService authorService;

    @MockBean
    GenreService genreService;


    @Autowired
    BookService service;

    @Test
    void saveBook() {
        when(authorService.findByName(any())).thenReturn(getAuthor());
        when(genreService.findByName(any())).thenReturn(getGenre());
        when(repository.findById(any())).thenReturn(Optional.of(getBook()));

        var bookDto = getBookDto();
        bookDto.setId(null);

        service.saveBook(bookDto);

        var savedBook = getBook();
        savedBook.setId(null);
        verify(repository, times(1)).save(eq((savedBook)));
    }

    @Test
    void updateBook() {
        when(authorService.findByName(any())).thenReturn(getAuthor());
        when(genreService.findByName(any())).thenReturn(getGenre());
        when(repository.findById(any())).thenReturn(Optional.of(getBook()));

        service.updateBook(getBookDto());

        verify(repository, times(1)).save(eq(getBook()));
        verify(repository, times(1)).findById(1L);
    }

    @Test
    void deleteBookById() {
        service.deleteBookById(Book_ID);

        //        verify(bookDao, times(1)).delete(book);
    }

    @Test
    void getAllBook() {
        when(repository.findAll()).thenReturn(getBookList());

        var actual = service.findAllBooks();

        assertEquals(getBookList(), actual);
    }

    @Test
    void findBookById() {
        when(repository.findById(eq(Book_ID))).thenReturn(Optional.of(getBook()));

        var actual = service.findBookById(Book_ID);

        assertEquals(getBook(), actual);
    }

    private List<Book> getBookList() {
        var book1 = getBook();
        book1.setId(2L);
        var book0 = getBook();
        List<Book> listBooks = new ArrayList<>();
        listBooks.add(book0);
        listBooks.add(book1);
        return listBooks;
    }

    private Book getBook() {
        return new Book(Book_ID, Book_NAME, getAuthor(), getGenre(), List.of(new Comment()));
    }


    private BookDto getBookDto() {
        return new BookDto(Book_ID, Book_NAME, AUTHOR_1, GENRE_2);
    }

    private Genre getGenre() {
        return new Genre(GENRE_ID, GENRE_NAME);
    }

    private Author getAuthor() {
        return new Author(AUTHOR_ID, AUTHOR_NAME);
    }

    private String getBookListString() {
        return EXPECTED_LIST_STRING;
    }
}