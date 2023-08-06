package ru.lazarev.springcourse.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import ru.lazarev.springcourse.domain.Question;
import ru.lazarev.springcourse.domain.User;
import ru.lazarev.springcourse.domain.UserAnswers;
import ru.lazarev.springcourse.repository.UserAnswerRepository;
import ru.lazarev.springcourse.service.IOService;
import ru.lazarev.springcourse.service.QuestionService;
import ru.lazarev.springcourse.service.TestingService;
import ru.lazarev.springcourse.service.utils.QuestionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TestingServiceImpl implements TestingService {

    IOService ioService;

    UserAnswerRepository userAnswerRepository;

    QuestionService questionService;

    @Override
    public void testingAndSaveAnswers(User user) {
        var questions = questionService.getQuestions();
        List<Integer> userAnswersList = new ArrayList<>();
        questions.forEach(question -> addUserAnswerInList(question, userAnswersList));
        saveQuestionsAndUserAnswers(user, userAnswersList, questions);
    }

    private void addUserAnswerInList(Question question, List<Integer> userAnswersList) {
        userAnswersList.add(ioService.readIntWithPrompt(QuestionUtils.getFormatString(question)));
    }

    private void saveQuestionsAndUserAnswers(User user, List<Integer> userAnswersList, List<Question> questionsList) {
        var userAnswers = new UserAnswers();
        userAnswers.setUser(user);
        userAnswers.setNumbersUserAnswersList(userAnswersList);
        userAnswers.setQuestions(questionsList);
        userAnswerRepository.save(userAnswers);
    }
}
