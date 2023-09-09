package ru.lazarev.springcourse.dao.impl;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@Import(GenreDaoJdbc.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
//@Profile("test")
class GenreDaoJdbcTest {

    public static final int EXPECTED_SIZE = 2;

    public static final String GENRE_NAME = "Genre 1";

    @Autowired
    GenreDaoJdbc genreDaoJdbc;

    @Test
    void findAll() {
        var result = genreDaoJdbc.findAll();
        assertEquals(EXPECTED_SIZE, result.size());
    }
}