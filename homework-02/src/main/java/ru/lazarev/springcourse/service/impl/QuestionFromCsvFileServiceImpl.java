package ru.lazarev.springcourse.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.lazarev.springcourse.domain.Question;
import ru.lazarev.springcourse.parser.Parser;
import ru.lazarev.springcourse.reader.Reader;
import ru.lazarev.springcourse.service.QuestionService;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class QuestionFromCsvFileServiceImpl implements QuestionService {

    Reader reader;

    Parser parser;

    @NonFinal
    @Value("${url.qa.file}")
    String fileName;

    @Override
    public List<Question> getQuestions() {
        return parser.parse(readCsv(fileName));
    }

    private String readCsv(String fileName) {
        return reader.readTextResource(fileName);
    }
}
