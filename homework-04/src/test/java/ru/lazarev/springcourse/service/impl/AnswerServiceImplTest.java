package ru.lazarev.springcourse.service.impl;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import ru.lazarev.springcourse.domain.Question;
import ru.lazarev.springcourse.domain.User;
import ru.lazarev.springcourse.domain.UserAnswers;
import ru.lazarev.springcourse.repository.UserAnswerRepository;
import ru.lazarev.springcourse.service.AnswerService;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ContextConfiguration(classes = AnswerServiceImpl.class)
@SpringBootTest
@FieldDefaults(level = AccessLevel.PRIVATE)
class AnswerServiceImplTest {

    public static final List<Integer> THREE_CORRECT_ANSWERS_LIST = List.of(1, 1, 1, 2, 2);
    public static final List<Integer> TWO_CORRECT_ANSWERS_LIST = List.of(1, 1, 2, 1, 1);
    public static final long COUNT_RIGHT_ANSWERS = 3L;

    @MockBean
    UserAnswerRepository userAnswerRepository;

    @Autowired
    AnswerService answerService;

    @BeforeAll
    public void setUp() {
        answerService = new AnswerServiceImpl(userAnswerRepository, COUNT_RIGHT_ANSWERS);
    }

    @Test
    void calculateCountRightAnswersThreeCorrectAnswersTest() {
        when(userAnswerRepository.getByUser(any())).thenReturn(getUserAnswers(THREE_CORRECT_ANSWERS_LIST));

        var actual = answerService.calculateCountRightAnswers(getUser());

        assertEquals(3, actual);
    }

    @Test
    void calculateCountRightAnswersTwoCorrectAnswersTest() {
        when(userAnswerRepository.getByUser(any())).thenReturn(getUserAnswers(TWO_CORRECT_ANSWERS_LIST));

        var actual = answerService.calculateCountRightAnswers(getUser());

        assertEquals(2, actual);
    }

    @Test
    void isPassedPassedTest() {
        when(userAnswerRepository.getByUser(any())).thenReturn(getUserAnswers(THREE_CORRECT_ANSWERS_LIST));

        var actual = answerService.isPassedTest(getUser());

        assertTrue(actual);
    }

    @Test
    void isPassedFailedTest() {
        when(userAnswerRepository.getByUser(any())).thenReturn(getUserAnswers(TWO_CORRECT_ANSWERS_LIST));

        var actual = answerService.isPassedTest(getUser());

        assertFalse(actual);
    }

    private UserAnswers getUserAnswers(List<Integer> numberUserAncwersList) {
        return UserAnswers.builder()
            .numbersUserAnswersList(numberUserAncwersList)
            .questions(getQuestionsList(1, 1, 1, 3, 3))
            .user(getUser())
            .build();
    }

    private List<Question> getQuestionsList(Integer... numberRightAnswer) {
        return IntStream
            .range(0, numberRightAnswer.length)
            .mapToObj(value -> new Question("question" + value, Collections.emptyList(), numberRightAnswer[value]))
            .toList();
    }

    private User getUser() {
        return new User(UUID.randomUUID(), "firstName", "lastName");
    }
}