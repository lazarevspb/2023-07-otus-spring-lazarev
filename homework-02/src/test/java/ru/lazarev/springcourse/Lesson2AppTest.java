package ru.lazarev.springcourse;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.lazarev.springcourse.parser.Parser;
import ru.lazarev.springcourse.reader.Reader;
import ru.lazarev.springcourse.service.AnswerService;
import ru.lazarev.springcourse.service.ApplicationRunner;
import ru.lazarev.springcourse.service.IOService;
import ru.lazarev.springcourse.service.InputService;
import ru.lazarev.springcourse.service.OutputService;
import ru.lazarev.springcourse.service.QuestionService;
import ru.lazarev.springcourse.service.TestingService;
import ru.lazarev.springcourse.service.UserService;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class Lesson2AppTest {

    @Test
    void contextLoads() {
        var context = new AnnotationConfigApplicationContext(Lesson2App.class);
        assertNotNull(context.getBean(Parser.class));
        assertNotNull(context.getBean(Reader.class));
        assertNotNull(context.getBean(TestingService.class));
        assertNotNull(context.getBean(QuestionService.class));
        assertNotNull(context.getBean(AnswerService.class));
        assertNotNull(context.getBean(ApplicationRunner.class));
        assertNotNull(context.getBean(InputService.class));
        assertNotNull(context.getBean(IOService.class));
        assertNotNull(context.getBean(OutputService.class));
        assertNotNull(context.getBean(QuestionService.class));
        assertNotNull(context.getBean(UserService.class));
        assertNotNull(context, "Application context is null");
    }
}