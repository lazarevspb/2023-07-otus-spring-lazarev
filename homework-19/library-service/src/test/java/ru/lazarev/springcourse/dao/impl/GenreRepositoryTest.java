package ru.lazarev.springcourse.dao.impl;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.lazarev.springcourse.library.domain.Genre;
import ru.lazarev.springcourse.library.repository.GenreRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@FieldDefaults(level = AccessLevel.PRIVATE)
class GenreRepositoryTest {

    public static final int EXPECTED_SIZE = 2;

    public static final long GENRE_ID = 1L;

    public static final String GENRE_NAME = "Genre 1";

    @Autowired
    GenreRepository dao;

    @Test
    void findAll() {
        var result = dao.findAll();
        assertEquals(EXPECTED_SIZE, result.size());
    }

    @Test
    void findById() {
        var actual = dao.findById(GENRE_ID);

        assertEquals(new Genre(GENRE_ID, GenreRepositoryTest.GENRE_NAME), actual.get());
    }
}