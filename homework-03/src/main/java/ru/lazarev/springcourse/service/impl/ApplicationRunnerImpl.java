package ru.lazarev.springcourse.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import ru.lazarev.springcourse.domain.User;
import ru.lazarev.springcourse.service.AnswerService;
import ru.lazarev.springcourse.service.IOService;
import ru.lazarev.springcourse.service.TestingService;
import ru.lazarev.springcourse.service.UserService;

import java.text.MessageFormat;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Component
public class ApplicationRunnerImpl implements ApplicationRunner {

    public static final String INTRODUCTION_MESSAGE = "{0} {1} please choose the correct answer number:";

    public static final String ENTER_FIRST_NAME_MESSAGE = "Enter your first name: ";

    public static final String ENTER_LAST_NAME_MESSAGE = "Enter your last name: ";

    public static final String YOUR_RESULT_MESSAGE = "Your result: ";

    public static final String GREETING_MESSAGE = "Welcome to the testing program!";

    public static final String TEST_PASSED_MESSAGE = "Test passed!";

    public static final String FAILED_TEST_MESSAGE = "You failed the test";


    IOService ioService;

    TestingService testingService;

    UserService userService;

    AnswerService answerService;

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
            print(TEST_PASSED_MESSAGE);
        } else {
            print(FAILED_TEST_MESSAGE);
        }
    }

    private void print(String message) {
        ioService.outputString(message);
    }

    private void printCountRightAnswers(long count) {
        print(YOUR_RESULT_MESSAGE + count);
    }

    private void showIntroduction(User user) {
        print(MessageFormat.format(INTRODUCTION_MESSAGE, user.getFirstName(),
                                   user.getSecondName()));
    }

    private User inputUserDataAndCreateUser() {
        String firstName = ioService.readStringWithPrompt(ENTER_FIRST_NAME_MESSAGE);
        String lastName = ioService.readStringWithPrompt(ENTER_LAST_NAME_MESSAGE);
        return userService.save(new User(null, firstName, lastName));
    }

    private void showGreeting() {
        print(GREETING_MESSAGE);
    }
}
