package ru.lazarev.springcourse;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.lazarev.springcourse.service.QuestionService;

public class App {
    public static final String SPRING_CONTEXT_XML_FILENAME = "/spring-context.xml";

    public static void main(String[] args) {
        var context = new ClassPathXmlApplicationContext(SPRING_CONTEXT_XML_FILENAME);
        context.getBean(QuestionService.class).process();
        context.close();
    }
}
