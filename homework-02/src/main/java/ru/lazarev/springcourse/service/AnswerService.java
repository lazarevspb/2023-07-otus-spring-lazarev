package ru.lazarev.springcourse.service;

import ru.lazarev.springcourse.domain.User;

public interface AnswerService {

    long calculateCountRightAnswers(User user);

    boolean isPassedTest(User user);
}
