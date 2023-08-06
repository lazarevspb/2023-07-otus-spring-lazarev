package ru.lazarev.springcourse;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import ru.lazarev.springcourse.service.ApplicationRunner;

@ComponentScan("ru.lazarev.springcourse")
@PropertySource("classpath:application.properties")
public class Lesson2App {

    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(Lesson2App.class);
        context.getBean(ApplicationRunner.class).run();
        context.close();
    }
}
