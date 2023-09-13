package ru.lazarev.springcourse.dao.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.lazarev.springcourse.domain.Author;
import ru.lazarev.springcourse.domain.Book;
import ru.lazarev.springcourse.domain.Comment;
import ru.lazarev.springcourse.domain.Genre;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@DataJpaTest
@Import(BookDaoJpa.class)
class BookDaoJpaTest {

    public static final int EXPECTED_SIZE = 3;
    public static final String AUTHOR_1_NAME = "Author 1";
    public static final String GENRE_2_NAME = "Genre 2";
    public static final String BOOK_1_TITLE = "Book 1";
    public static final long BOOK_ID = 1L;
    public static final long UPDATED_BOOK_ID = 2L;
    public static final long SAVED_BOOK_ID = 3L;

    @Autowired
    private BookDaoJpa dao;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    void findById() {
        var result = dao.findById(BOOK_ID);

        assertEquals(getBook(), result);
    }

    @Test
    void findAll() {
        var result = dao.findAll();
        assertEquals(EXPECTED_SIZE, result.size());
    }

    @Test
    void save() {
        var bookForSave = getBookForSave();

        dao.save(bookForSave);

        var actual = entityManager.find(Book.class, SAVED_BOOK_ID);

        assertEquals(bookForSave, actual);
    }

    @Test
    void update() {
        var bookForUpdate = getBook();
        bookForUpdate.setId(UPDATED_BOOK_ID);

        dao.save(bookForUpdate);

        var actual = entityManager.find(Book.class, UPDATED_BOOK_ID);
        assertEquals(bookForUpdate, actual);
    }

    @Test
    void delete() {
        assertNotNull(entityManager.find(Book.class, 2L));

        //        dao.delete(book);
        entityManager.clear();

        assertNull(entityManager.find(Book.class, 2L));
    }

    private Book getBook() {
        var author = new Author(BOOK_ID, AUTHOR_1_NAME);
        var genre = new Genre(UPDATED_BOOK_ID, GENRE_2_NAME);
        return new Book(BOOK_ID, BOOK_1_TITLE, author, genre, List.of(new Comment()));
    }

    private Book getBookForSave() {
        var author = new Author(BOOK_ID, AUTHOR_1_NAME);
        var genre = new Genre(UPDATED_BOOK_ID, GENRE_2_NAME);
        return new Book(SAVED_BOOK_ID, BOOK_1_TITLE, author, genre, List.of(new Comment()));
    }
}