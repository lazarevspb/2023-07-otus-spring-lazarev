package ru.lazarev.springcourse.dao.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;
import ru.lazarev.springcourse.domain.Book;
import ru.lazarev.springcourse.dto.AuthorDto;
import ru.lazarev.springcourse.dto.BookDto;
import ru.lazarev.springcourse.dto.GenreDto;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Import(BookDaoJdbc.class)
class BookDaoJdbcTest {

    public static final String SAVED_BOOK_TITLE = "saved book";

    public static final String EXPECTED_BOOK_TITLE = "Book 1";

    public static final int EXPECTED_SIZE = 3;
    public static final String AUTHOR_NAME = "Author 2";
    public static final String AUTHOR_NAME_2 = "Author 1";
    public static final String GENRE_NAME = "Genre 2";

    @Autowired
    private BookDaoJdbc dao;

    @Test
    void findById() {
        var result = dao.findById(1L);

        assertEquals(getExpectedBook(), result);
    }

    @Test
    void findAll() {
        var result = dao.findAll();
        assertEquals(EXPECTED_SIZE, result.size());
    }

    @Test
    void save() {
        var bookForSave = getBook();

        dao.save(bookForSave);

        assertEquals(4, dao.findAll().size());
    }

    @Test
    void update() {
        var bookForUpdate = getBook();
        bookForUpdate.setId(2L);

        dao.update(bookForUpdate);

        assertEquals(getExpectedSavedBook(), dao.findById(2L));
    }

    @Test
    void delete() {
        assertThatCode(() -> dao.findById(1L))
            .doesNotThrowAnyException();

        dao.delete(1L);

        assertThatThrownBy(() -> dao.findById(1L))
            .isInstanceOf(EmptyResultDataAccessException.class);
    }

    private BookDto getExpectedBook() {
        var expected = new BookDto();
        expected.setTitle(EXPECTED_BOOK_TITLE);
        expected.setId(1L);
        expected.setAuthor(new AuthorDto(1L, AUTHOR_NAME_2));
        expected.setGenre(new GenreDto(2L, GENRE_NAME));
        return expected;
    }

    private BookDto getExpectedSavedBook() {
        var expected = new BookDto();
        expected.setTitle(SAVED_BOOK_TITLE);
        expected.setId(2L);
        expected.setAuthor(new AuthorDto(2L, AUTHOR_NAME));
        expected.setGenre(new GenreDto(2L, GENRE_NAME));
        return expected;
    }

    private Book getBook() {
        var bookForSave = new Book();
        bookForSave.setTitle(SAVED_BOOK_TITLE);
        bookForSave.setAuthorId(2L);
        bookForSave.setGenreId(2L);
        return bookForSave;
    }
}