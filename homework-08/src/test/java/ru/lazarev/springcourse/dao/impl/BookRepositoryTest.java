package ru.lazarev.springcourse.dao.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.annotation.DirtiesContext;
import ru.lazarev.springcourse.domain.Author;
import ru.lazarev.springcourse.domain.Book;
import ru.lazarev.springcourse.domain.Comment;
import ru.lazarev.springcourse.domain.Genre;
import ru.lazarev.springcourse.repository.BookRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@DataMongoTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class BookRepositoryTest {

    public static final int EXPECTED_SIZE = 2;
    public static final String AUTHOR_1_NAME = "Author 1";
    public static final String GENRE_2_NAME = "Genre 2";
    public static final String BOOK_1_TITLE = "Book 1";
    public static final String UPDATED_BOOK_ID = "2";
    public static final String SAVED_BOOK_ID = "3";
    public static final String BOOK_ID = "6517f6fcfaa2217f18e0c6b0";
    private static final String GENRE_2_ID = "6517f6fcfaa2217f18e0c6g1";
    public static final String COMMENT_1_ID = "6517f6fcfaa2217f18e0c6c0";
    private static final String AUTHOR_1_ID = "6517f6fcfaa2217f18e0c6a0";

    @Autowired
    private BookRepository repository;


    @Test
    void findById() {
        var result = repository.findById(BOOK_ID);

        assertEquals(getBook(), result.get());
    }

    @Test
    void findAll() {
        var result = repository.findAll();
        assertEquals(EXPECTED_SIZE, result.size());
    }

    @Test
    void save() {
        var bookForSave = getBookForSave();

        var saved = repository.save(bookForSave);

        var actual = repository.findById(saved.getId());

        assertEquals(bookForSave, actual.get());
    }

    @Test
    void update() {
        var bookForUpdate = getBook();
        bookForUpdate.setId(UPDATED_BOOK_ID);

        var actual = repository.save(bookForUpdate);

        assertEquals(bookForUpdate, actual);
    }

    @Test
    void delete() {
        repository.deleteById(BOOK_ID);

        var book = repository.findById(BOOK_ID);

        assertFalse(book.isPresent());
    }

    private Book getBook() {
        var author = new Author(AUTHOR_1_ID, AUTHOR_1_NAME);
        var genre = new Genre(GENRE_2_ID, GENRE_2_NAME);
        return new Book(BOOK_ID, BOOK_1_TITLE, author, genre, getCommentList());
    }

    private List<Comment> getCommentList() {
        var comment0 = new Comment(COMMENT_1_ID, "comment 1 book 1", null);
        return List.of(comment0);
    }

    private Book getBookForSave() {
        var author = new Author(AUTHOR_1_ID, AUTHOR_1_NAME);
        var genre = new Genre(GENRE_2_ID, GENRE_2_NAME);
        return new Book(SAVED_BOOK_ID, BOOK_1_TITLE, author, genre, null);
    }
}