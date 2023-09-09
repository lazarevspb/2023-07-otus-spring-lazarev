package ru.lazarev.springcourse.dao.impl;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.assertEquals;

@JdbcTest
@Import(AuthorDaoJdbc.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
class AuthorDaoJdbcTest {

    public static final int EXPECTED_SIZE = 2;

    @Autowired
    AuthorDaoJdbc dao;

    @Test
    void findAll() {
        var result = dao.findAll();
        assertEquals(EXPECTED_SIZE, result.size());
    }
}