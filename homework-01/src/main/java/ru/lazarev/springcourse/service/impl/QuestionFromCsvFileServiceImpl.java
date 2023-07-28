package ru.lazarev.springcourse.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import ru.lazarev.springcourse.domain.Question;
import ru.lazarev.springcourse.parser.Parser;
import ru.lazarev.springcourse.printer.Printer;
import ru.lazarev.springcourse.reader.Reader;
import ru.lazarev.springcourse.service.QuestionService;

import java.util.List;

@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class QuestionFromCsvFileServiceImpl implements QuestionService {
    Reader reader;

    Parser parser;

    Printer printer;

    String fileName;

    @Override
    public void process() {
        printer.printToScreen(getQuestions(fileName));
    }

    private List<Question> getQuestions(String fileName) {
        return parser.parse(readCsv(fileName));
    }

    private String readCsv(String fileName) {
        return reader.readTextResource(fileName);
    }
}
