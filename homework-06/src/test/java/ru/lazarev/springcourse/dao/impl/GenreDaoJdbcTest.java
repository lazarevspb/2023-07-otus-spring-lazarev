package ru.lazarev.springcourse.dao.impl;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import ru.lazarev.springcourse.domain.Genre;

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
    void findById() {
        var result = genreDaoJdbc.findById(1L);

        assertEquals(getExpectedGenre(), result);
    }

    @Test
    void findAll() {
        var result = genreDaoJdbc.findAll();
        assertEquals(EXPECTED_SIZE, result.size());
    }

    private Genre getExpectedGenre() {
        var expected = new Genre();
        expected.setName(GENRE_NAME);
        expected.setId(1L);
        return expected;
    }
}