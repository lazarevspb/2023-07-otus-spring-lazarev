package ru.lazarev.springcourse.printer.impl;

import ru.lazarev.springcourse.domain.Question;
import ru.lazarev.springcourse.printer.Printer;

import java.text.MessageFormat;
import java.util.List;
import java.util.stream.Collectors;

public class PrinterImpl implements Printer {
    public static final String QUESTION_FORMAT_STRING = "Question: \n{0};\n {1}";

    public static final String ANSWERS_OPTIONS_STRING = "\nAnswer options:\n";

    @Override
    public void printToScreen(List<Question> questions) {
        questions.forEach(this::printQuestion);
    }

    private void printQuestion(Question question) {
        System.out.println(getFormatString(question));
    }

    private String getFormatString(Question question) {
        return MessageFormat
                .format(QUESTION_FORMAT_STRING,
                        question.getQuestion(),
                        question.getAnswers().stream()
                                .collect(Collectors
                                        .joining(", ", ANSWERS_OPTIONS_STRING, ";\n")).replace("\r", ""));
    }
}

