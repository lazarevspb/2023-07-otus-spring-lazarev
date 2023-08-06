package ru.lazarev.springcourse.service;

import ru.lazarev.springcourse.domain.Question;

import java.util.List;

public interface QuestionService {

    List<Question> getQuestions();
}
