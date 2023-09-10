package ru.lazarev.springcourse.dao.impl;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.lazarev.springcourse.domain.Author;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@Import(AuthorDaoJpa.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
class AuthorDaoJpaTest {

    public static final int EXPECTED_SIZE = 2;

    public static final long AUTHOR_ID = 1L;

    public static final String AUTHOR_NAME = "Author 1";

    @Autowired
    AuthorDaoJpa dao;

    @Test
    void findById() {
        var actual = dao.findById(AUTHOR_ID);

        assertEquals(new Author(AUTHOR_ID, AUTHOR_NAME), actual.get());
    }

    @Test
    void findAll() {
        var result = dao.findAll();
        assertEquals(EXPECTED_SIZE, result.size());
    }
}