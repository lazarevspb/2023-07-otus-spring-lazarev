package ru.lazarev.springcourse.repository;

import ru.lazarev.springcourse.domain.User;
import ru.lazarev.springcourse.domain.UserAnswers;

public interface UserAnswerRepository {

    UserAnswers save(UserAnswers userAnswers);

    UserAnswers getByUser(User user);
}
