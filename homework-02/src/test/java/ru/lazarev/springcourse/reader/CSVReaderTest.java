package ru.lazarev.springcourse.reader;

import org.junit.jupiter.api.Test;
import ru.lazarev.springcourse.reader.impl.CSVReaderImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CSVReaderTest {
    public static final String CSV_FILENAME = "qa.csv";
    public static final String NOT_FOUND_CSV_FILENAME = "not_found.csv";
    public static final String EXPECTED_EXCEPTION_MESSAGE = "Failed to get resource with filename: [not_found.csv]";

    @Test
    void readTextResourceTest() {
        var actual = new CSVReaderImpl().readTextResource(CSV_FILENAME);

        assertEquals(expectedString(), actual);
    }

    @Test
    void readTextResourceThrowExceptionTest() {
        var expectedException = assertThrows(RuntimeException.class, () -> new CSVReaderImpl().readTextResource(NOT_FOUND_CSV_FILENAME));

        assertEquals(EXPECTED_EXCEPTION_MESSAGE, expectedException.getMessage());
    }

    private String expectedString() {
        return """
                Question_01_text;1. Answer_01_text\r
                Question_02_text;1. Answer_01_text,2. Answer_02_text,3. Answer_03_text\r
                Question_03_text;1. Answer_01_text,2. Answer_02_text,3. Answer_03_text""";
    }
}