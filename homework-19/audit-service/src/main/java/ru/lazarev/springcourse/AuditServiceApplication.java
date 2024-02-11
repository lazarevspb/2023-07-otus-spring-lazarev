package ru.lazarev.springcourse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AuditServiceApplication {
    //	http://localhost:8084/audit/events/2
    public static void main(String[] args) {
        SpringApplication.run(AuditServiceApplication.class, args);
    }
}