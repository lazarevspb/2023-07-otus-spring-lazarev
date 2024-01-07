package ru.lazarev.springcourse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "ru.lazarev.springcourse.feign")
public class StoreApp {
    //    http://localhost:8080
    public static void main(String[] args) {
        SpringApplication.run(StoreApp.class, args);
    }
}
