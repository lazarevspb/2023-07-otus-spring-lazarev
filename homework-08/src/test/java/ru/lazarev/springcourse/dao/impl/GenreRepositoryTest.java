package ru.lazarev.springcourse.dao.impl;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import ru.lazarev.springcourse.domain.Genre;
import ru.lazarev.springcourse.repository.GenreRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataMongoTest
@FieldDefaults(level = AccessLevel.PRIVATE)
class GenreRepositoryTest {

    public static final int EXPECTED_SIZE = 2;

    private static final String GENRE_1_ID = "6517f6fcfaa2217f18e0c6g0";

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
        var actual = dao.findById(GENRE_1_ID);

        assertEquals(new Genre(GENRE_1_ID, GenreRepositoryTest.GENRE_NAME), actual.get());
    }
}