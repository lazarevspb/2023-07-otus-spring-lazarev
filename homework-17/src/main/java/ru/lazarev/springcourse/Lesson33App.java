package ru.lazarev.springcourse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Lesson33App {
//    http://localhost:8080/books admin@1234
//    http://localhost:8080/actuator/metrics
//    http://localhost:8080/actuator/health
    public static void main(String[] args) {
        SpringApplication.run(Lesson33App.class, args);
    }
}
