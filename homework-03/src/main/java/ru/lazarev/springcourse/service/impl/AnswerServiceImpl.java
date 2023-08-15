package ru.lazarev.springcourse.service.impl;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.lazarev.springcourse.domain.User;
import ru.lazarev.springcourse.domain.UserAnswers;
import ru.lazarev.springcourse.repository.UserAnswerRepository;
import ru.lazarev.springcourse.service.AnswerService;

import java.util.stream.IntStream;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AnswerServiceImpl implements AnswerService {

    UserAnswerRepository userAnswerRepository;

    Long countRightAnswers;

    public AnswerServiceImpl(UserAnswerRepository userAnswerRepository,
                             @Value("${number.correct.answer}") Long countRightAnswers) {
        this.userAnswerRepository = userAnswerRepository;
        this.countRightAnswers = countRightAnswers;
    }


    @Override
    public long calculateCountRightAnswers(User user) {
        var userAnswers = getUserAnswers(user);
        return IntStream.range(0, userAnswers.getQuestions().size())
            .filter(i -> isRightAnswer(i, userAnswers))
            .count();
    }

    @Override
    public boolean isPassedTest(User user) {
        return calculateCountRightAnswers(user) >= countRightAnswers;
    }

    private static boolean isRightAnswer(int i, UserAnswers userAnswers) {
        return userAnswers.getNumbersUserAnswersList().get(i)
            .equals(userAnswers.getQuestions().get(i).numberCorrectAnswer());
    }

    private UserAnswers getUserAnswers(User user) {
        return userAnswerRepository.getByUser(user);
    }
}