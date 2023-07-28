package ru.lazarev.springcourse.printer;

import ru.lazarev.springcourse.domain.Question;

import java.util.List;

public interface Printer {
    void printToScreen(List<Question> questions);
}
