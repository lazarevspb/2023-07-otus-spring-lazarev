package ru.lazarev.springcourse;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

@SpringBootTest(properties = "spring.shell.interactive.enabled=false")
class Lesson7AppIntegrationTest {

    private static final InputStream ORIGINAL_SYSTEM_IN = System.in;
    private static final String FIRST_NAME = "firstName";
    private static final String LAST_NAME = "lastName";
    private static final String USER_ANSWER = "1";
    private static final String DELIMITER = "\n";

    @BeforeAll
    public static void setUp() {
        System.setIn(new ByteArrayInputStream(getUserTestInput().getBytes()));
    }

    @AfterAll
    public static void tearDown() {
        System.setIn(ORIGINAL_SYSTEM_IN);
    }

    @Test
    void contextLoads() {}

    private static String getUserTestInput() {
        return String.join(DELIMITER,
                           FIRST_NAME,
                           LAST_NAME,
                           USER_ANSWER,
                           USER_ANSWER,
                           USER_ANSWER,
                           USER_ANSWER,
                           USER_ANSWER);
    }
}