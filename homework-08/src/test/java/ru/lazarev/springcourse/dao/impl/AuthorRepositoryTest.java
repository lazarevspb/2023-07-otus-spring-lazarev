package ru.lazarev.springcourse.dao.impl;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import ru.lazarev.springcourse.domain.Author;
import ru.lazarev.springcourse.repository.AuthorRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataMongoTest
@FieldDefaults(level = AccessLevel.PRIVATE)
class AuthorRepositoryTest {

    public static final int EXPECTED_SIZE = 2;

    private static final String AUTHOR_1_ID = "6517f6fcfaa2217f18e0c6a0";
    public static final String AUTHOR_NAME = "Author 1";

    @Autowired
    AuthorRepository repository;

    @Test
    void findById() {
        var actual = repository.findById(AUTHOR_1_ID);

        assertEquals(new Author(AUTHOR_1_ID, AUTHOR_NAME), actual.get());
    }

    @Test
    void findAll() {
        var result = repository.findAll();
        assertEquals(EXPECTED_SIZE, result.size());
    }
}