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
import ru.lazarev.springcourse.dto.CommentDto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@DataJpaTest
@Import(CommentDaoJpa.class)
class CommentDaoTest {

    public static final long BOOK_ID = 2L;
    public static final long COMMENT_ID = 1L;
    public static final String TEST_COMMENT_TEXT = "test comment";
    public static final long SAVED_COMMENT_ID = 6L;
    public static final String UPDATED_COMMENT_TEXT = "updated comment";
    public static final String COMMENT_1_BOOK_TEXT = "comment 1 book 1";
    public static final String BOOK_1_TITLE = "Book 1";
    public static final String AUTHOR_1_NAME = "Author 1";
    public static final String GENRE_2_NAME = "Genre 2";

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CommentDaoJpa commentDao;

    @Test
    void findAllCommentByBookId() {
        var comments = commentDao.findAllCommentByBookId(BOOK_ID);

        assertEquals(2, comments.size());
    }

    @Test
    void findById() {
        var actual = commentDao.findById(COMMENT_ID);

        assertEquals(getExpectedComment(), actual);
    }

    @Test
    void save() {
        var commentDto = new CommentDto();
        commentDto.setText(TEST_COMMENT_TEXT);
        commentDto.setBookId(COMMENT_ID);

        commentDao.save(commentDto);

        var savedComment = entityManager.find(Comment.class, SAVED_COMMENT_ID);
        assertNotNull(savedComment);
        assertEquals(TEST_COMMENT_TEXT, savedComment.getText());
        assertEquals(COMMENT_ID, savedComment.getBook().getId());
    }

    @Test
    void update() {
        var updatedCommentDto = new CommentDto();
        updatedCommentDto.setId(COMMENT_ID);
        updatedCommentDto.setText(UPDATED_COMMENT_TEXT);

        commentDao.update(updatedCommentDto);

        var updatedComment = entityManager.find(Comment.class, COMMENT_ID);
        assertEquals(updatedCommentDto.getText(), updatedComment.getText());
    }

    @Test
    void delete() {
        assertNotNull(entityManager.find(Comment.class, COMMENT_ID));

        commentDao.delete(COMMENT_ID);
        entityManager.clear();

        assertNull(entityManager.find(Comment.class, COMMENT_ID));
    }

    private Comment getExpectedComment() {
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