package ru.lazarev.springcourse.dao.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.lazarev.springcourse.domain.Author;
import ru.lazarev.springcourse.domain.Book;
import ru.lazarev.springcourse.domain.Comment;
import ru.lazarev.springcourse.domain.Genre;
import ru.lazarev.springcourse.dto.CommentDto;
import ru.lazarev.springcourse.repository.CommentRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@DataJpaTest
class CommentDaoTest {

    public static final long BOOK_ID = 2L;
    public static final long COMMENT_ID = 1L;
    public static final String TEST_COMMENT_TEXT = "test comment";
    public static final String UPDATED_COMMENT_TEXT = "updated comment";
    public static final String COMMENT_1_BOOK_TEXT = "comment 1 book 1";
    public static final String BOOK_1_TITLE = "Book 1";
    public static final String AUTHOR_1_NAME = "Author 1";
    public static final String GENRE_2_NAME = "Genre 2";

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CommentRepository repository;

    @Test
    void findById() {
        var actual = repository.findById(COMMENT_ID);

        assertEquals(getComment(), actual.get());
    }

    @Test
    void save() {
        var commentDto = new CommentDto();
        commentDto.setText(TEST_COMMENT_TEXT);
        commentDto.setBookId(COMMENT_ID);

        var save = repository.save(getComment());

        var savedComment = entityManager.find(Comment.class, save.getId());
        assertNotNull(savedComment);
        assertEquals(COMMENT_1_BOOK_TEXT, savedComment.getText());
        assertEquals(COMMENT_ID, savedComment.getBook().getId());
    }

    @Test
    void update() {
        var updatedCommentDto = getComment();
        updatedCommentDto.setId(COMMENT_ID);
        updatedCommentDto.setText(UPDATED_COMMENT_TEXT);

        repository.save(updatedCommentDto);

        var updatedComment = entityManager.find(Comment.class, COMMENT_ID);
        assertEquals(updatedCommentDto.getText(), updatedComment.getText());
    }

    @Test
    void delete() {
        assertNotNull(entityManager.find(Comment.class, COMMENT_ID));

        repository.deleteById(COMMENT_ID);

        assertNull(entityManager.find(Comment.class, COMMENT_ID));
    }

    private Comment getComment() {
        var comment = new Comment();
        comment.setId(COMMENT_ID);
        comment.setText(COMMENT_1_BOOK_TEXT);

        var book = new Book();
        book.setId(COMMENT_ID);
        book.setTitle(BOOK_1_TITLE);

        var author = new Author();
        author.setId(COMMENT_ID);
        author.setName(AUTHOR_1_NAME);

        var genre = new Genre();
        genre.setId(BOOK_ID);
        genre.setName(GENRE_2_NAME);

        book.setAuthor(author);
        book.setGenre(genre);

        comment.setBook(book);
        return comment;
    }
}