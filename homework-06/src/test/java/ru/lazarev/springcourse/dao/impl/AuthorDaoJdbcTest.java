package ru.lazarev.springcourse.dao.impl;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.lazarev.springcourse.domain.Author;

import static org.junit.jupiter.api.Assertions.assertEquals;

@JdbcTest
@Import(AuthorDaoJdbc.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
class AuthorDaoJdbcTest {

    public static final int EXPECTED_SIZE = 2;

    public static final String AUTHOR_NAME = "Author 1";

    @Autowired
    AuthorDaoJdbc dao;

    @Test
    void findById() {
        var result = dao.findById(1L);

        assertEquals(getExpectedAuthor(), result);
    }

    @Test
    void findAll() {
        var result = dao.findAll();
        assertEquals(EXPECTED_SIZE, result.size());
    }

    private Author getExpectedAuthor() {
        var expected = new Author();
        expected.setName(AUTHOR_NAME);
        expected.setId(1L);
        return expected;
    }
}