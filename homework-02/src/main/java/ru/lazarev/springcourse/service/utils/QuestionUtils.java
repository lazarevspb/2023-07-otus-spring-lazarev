package ru.lazarev.springcourse.service.utils;

import lombok.experimental.UtilityClass;
import ru.lazarev.springcourse.domain.Question;

import java.text.MessageFormat;
import java.util.stream.Collectors;

@UtilityClass
public class QuestionUtils {
    public static final String QUESTION_FORMAT_STRING = "Question: \n{0};\n {1}";

    public static final String ANSWERS_OPTIONS_STRING = "\nAnswer options:\n";

    public String getFormatString(Question question) {
        return MessageFormat
            .format(QUESTION_FORMAT_STRING,
                    question.getQuestion(),
                    question.getAnswers().stream()
                        .collect(Collectors
                                     .joining(", ", ANSWERS_OPTIONS_STRING, ";\n")).replace("\r", ""));
    }
}
