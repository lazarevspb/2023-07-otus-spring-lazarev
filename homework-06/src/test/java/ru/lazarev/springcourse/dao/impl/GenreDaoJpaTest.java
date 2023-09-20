package ru.lazarev.springcourse.dao.impl;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.lazarev.springcourse.domain.Genre;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@Import(GenreDaoJpa.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
class GenreDaoJpaTest {

    public static final int EXPECTED_SIZE = 2;

    public static final long GENRE_ID = 1L;

    public static final String GENRE_NAME = "Genre 1";

    @Autowired
    GenreDaoJpa dao;

    @Test
    void findAll() {
        var result = dao.findAll();
        assertEquals(EXPECTED_SIZE, result.size());
    }

    @Test
    void findById() {
        var actual = dao.findById(GENRE_ID);

        assertEquals(new Genre(GENRE_ID, GenreDaoJpaTest.GENRE_NAME), actual.get());
    }
}