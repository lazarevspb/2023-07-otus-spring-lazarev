package ru.lazarev.springcourse;

import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.lazarev.springcourse.parser.Parser;
import ru.lazarev.springcourse.printer.Printer;
import ru.lazarev.springcourse.reader.Reader;
import ru.lazarev.springcourse.service.QuestionService;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class AppTest {

    @Test
    void contextLoads() {
        var context = new ClassPathXmlApplicationContext("/spring-context.xml");
        assertNotNull(context.getBean(Parser.class));
        assertNotNull(context.getBean(Printer.class));
        assertNotNull(context.getBean(Reader.class));
        assertNotNull(context.getBean(QuestionService.class));
        assertNotNull(context, "Application context is null");
    }
}