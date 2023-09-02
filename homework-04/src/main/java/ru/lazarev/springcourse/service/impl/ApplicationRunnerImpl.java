package ru.lazarev.springcourse.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import ru.lazarev.springcourse.domain.User;
import ru.lazarev.springcourse.service.AnswerService;
import ru.lazarev.springcourse.service.IOService;
import ru.lazarev.springcourse.service.LocalizationService;
import ru.lazarev.springcourse.service.TestingService;
import ru.lazarev.springcourse.service.UserService;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Component
@Primary
public class ApplicationRunnerImpl implements ApplicationRunner {

    public static final String INTRODUCTION_MESSAGE = "introduction.message";

    public static final String ENTER_FIRST_NAME_MESSAGE = "enter.first.name.message";

    public static final String ENTER_LAST_NAME_MESSAGE = "enter.last.name.message";

    public static final String YOUR_RESULT_MESSAGE = "your.result.message";

    public static final String GREETING_MESSAGE = "greeting.message";

    public static final String TEST_PASSED_MESSAGE = "test.passed.message";

    public static final String FAILED_TEST_MESSAGE = "failed.test.message";


    IOService ioService;

    TestingService testingService;

    UserService userService;

    AnswerService answerService;

    LocalizationService localizationService;

    @Override
    public void run(ApplicationArguments args) {
        showGreeting();
        var user = inputUserDataAndCreateUser();
        showIntroduction(user);

        testingService.testingAndSaveAnswers(user);

        printCountRightAnswers(answerService.calculateCountRightAnswers(user));

        printResult(user);
    }


    private void printResult(User user) {
        if (answerService.isPassedTest(user)) {
            print(localizationService.getMessage(TEST_PASSED_MESSAGE));
        } else {
            print(localizationService.getMessage(FAILED_TEST_MESSAGE));
        }
    }

    private void print(String message) {
        ioService.outputString(message);
    }

    private void printCountRightAnswers(long count) {
        print(localizationService.getMessage(YOUR_RESULT_MESSAGE) + count);
    }

    private void showIntroduction(User user) {
        print(localizationService.getMessage(INTRODUCTION_MESSAGE, user.getFirstName(),
                                             user.getSecondName()));
    }

    private User inputUserDataAndCreateUser() {
        String firstName = ioService.readStringWithPrompt(localizationService.getMessage(ENTER_FIRST_NAME_MESSAGE));
        String lastName = ioService.readStringWithPrompt(localizationService.getMessage(ENTER_LAST_NAME_MESSAGE));
        return userService.save(new User(null, firstName, lastName));
    }

    private void showGreeting() {
        print(localizationService.getMessage(GREETING_MESSAGE));
    }
}
