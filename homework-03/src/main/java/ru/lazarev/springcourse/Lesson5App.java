package ru.lazarev.springcourse;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import ru.lazarev.springcourse.service.ApplicationRunner;

@ComponentScan
@PropertySource("classpath:application.properties")
public class Lesson5App {

    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(Lesson5App.class);
        context.getBean(ApplicationRunner.class).run();
        context.close();
    }
}
