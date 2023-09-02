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
import ru.lazarev.springcourse.service.LocaleProvider;
import ru.lazarev.springcourse.service.QuestionService;

import java.text.MessageFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class QuestionFromCsvFileServiceImpl implements QuestionService {

    public static final String FILE_NAME_PATTERN = "{0}_{1}.csv";

    Reader reader;

    Parser parser;

    LocaleProvider localeProvider;

    @NonFinal
    @Value("${url.qa.file}")
    String fileName;

    @Override
    public List<Question> getQuestions() {
        return parser.parse(readCsv(fileName));
    }

    private String readCsv(String fileName) {
        return reader.readTextResource(
            MessageFormat.format(FILE_NAME_PATTERN, fileName, localeProvider.getCurrent()));
    }
}
