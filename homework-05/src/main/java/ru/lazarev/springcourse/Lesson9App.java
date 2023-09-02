package ru.lazarev.springcourse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

@SpringBootApplication
public class Lesson9App {

    public static void main(String[] args) throws SQLException {
        SpringApplication.run(Lesson9App.class, args);
    }
}
