package ru.lazarev.springcourse.parser.impl;

import ru.lazarev.springcourse.domain.Question;
import ru.lazarev.springcourse.parser.Parser;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CSVParserImpl implements Parser {
    public List<Question> parse(String csvString) {
        return Arrays.stream(csvString.split("\n"))
                .map(s -> s.split(";"))
                .map(this::convertToQuestion)
                .collect(Collectors.toList());
    }

    private Question convertToQuestion(String[] strings) {
        return new Question(strings[0], Arrays.stream(strings[1].split(",")).toList());
    }
}
