package ru.lazarev.springcourse.parser;

import org.junit.jupiter.api.Test;
import ru.lazarev.springcourse.domain.Question;
import ru.lazarev.springcourse.parser.impl.CSVParserImpl;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CSVParserTest {

    @Test
    void parseTest() {
        var actual = new CSVParserImpl().parse(getCsvString());

        assertEquals(getExpectedList(), actual);
    }

    private String getCsvString() {
        return "Question_01_text;1. Answer_01_text\n" +
                "Question_02_text;1. Answer_01_text,2. Answer_02_text,3. Answer_03_text";
    }

    private List<Question> getExpectedList() {
        var question0 = new Question("Question_01_text", List.of("1. Answer_01_text"));
        var question1 = new Question("Question_02_text", List.of(
                "1. Answer_01_text",
                "2. Answer_02_text",
                "3. Answer_03_text"
        ));
        return List.of(question0, question1);
    }
}