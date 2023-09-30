package ru.lazarev.springcourse.db.migration;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.github.cloudyrock.mongock.driver.mongodb.springdata.v3.decorator.impl.MongockTemplate;
import ru.lazarev.springcourse.domain.Author;
import ru.lazarev.springcourse.domain.Book;
import ru.lazarev.springcourse.domain.Comment;
import ru.lazarev.springcourse.domain.Genre;

import java.util.List;

@ChangeLog
public class DatabaseChangelog {
    Author savedAuthor1;
    Author savedAuthor2;
    Genre savedGenre1;
    Genre savedGenre2;
    Book savedBook1;
    Book savedBook2;

    @ChangeSet(order = "001", id = "addAuthors", author = "vvlazarev")
    public void addAuthors(MongockTemplate mongoTemplate) {
        Author author1 = new Author();
        author1.setName("Author 1");
        author1.setId("6517f6fcfaa2217f18e0c6a0");

        Author author2 = new Author();
        author2.setName("Author 2");
        author2.setId("6517f6fcfaa2217f18e0c6a2");

        savedAuthor1 = mongoTemplate.save(author1);
        savedAuthor2 = mongoTemplate.save(author2);
    }


    @ChangeSet(order = "002", id = "addGenres", author = "vvlazarev")
    public void addGenres(MongockTemplate mongoTemplate) {
        Genre genre1 = new Genre();
        genre1.setName("Genre 1");
        genre1.setId("6517f6fcfaa2217f18e0c6g0");

        Genre genre2 = new Genre();
        genre2.setName("Genre 2");
        genre2.setId("6517f6fcfaa2217f18e0c6g1");

        savedGenre1 = mongoTemplate.save(genre1);
        savedGenre2 = mongoTemplate.save(genre2);
    }


    @ChangeSet(order = "003", id = "addBooks", author = "vvlazarev")
    public void addBooks(MongockTemplate mongoTemplate) {
        Book book1 = new Book();
        book1.setTitle("Book 1");
        book1.setAuthor(savedAuthor1);
        book1.setGenre(savedGenre2);
        book1.setId("6517f6fcfaa2217f18e0c6b0");

        Book book2 = new Book();
        book2.setTitle("Book 2");
        book2.setAuthor(savedAuthor2);
        book2.setGenre(savedGenre2);
        book2.setId("6517f6fcfaa2217f18e0c6b1");

        savedBook1 = mongoTemplate.save(book1);
        savedBook2 = mongoTemplate.save(book2);
    }

    @ChangeSet(order = "004", id = "addComments", author = "vvlazarev")
    public void addComments(MongockTemplate mongoTemplate) {
        Comment comment1 = new Comment();
        comment1.setText("comment 1 book 1");
        comment1.setBook(savedBook1);
        comment1.setId("6517f6fcfaa2217f18e0c6c0");

        Comment comment2 = new Comment();
        comment2.setText("comment 2 book 1");
        comment2.setBook(savedBook1);
        comment2.setId("6517f6fcfaa2217f18e0c6c1");

        Comment comment3 = new Comment();
        comment3.setText("comment_1 book 2");
        comment3.setBook(savedBook2);
        comment3.setId("6517f6fcfaa2217f18e0c6c2");

        mongoTemplate.save(comment1);
        mongoTemplate.save(comment2);
        mongoTemplate.save(comment3);

        savedBook1.setComments(List.of(comment1));
        savedBook2.setComments(List.of(comment2, comment3));

        mongoTemplate.save(savedBook1);
        mongoTemplate.save(savedBook2);
    }
}



