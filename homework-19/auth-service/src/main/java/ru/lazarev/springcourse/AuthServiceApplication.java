package ru.lazarev.springcourse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AuthServiceApplication {
	//http://localhost:8081
	// user@user
	//admin@admin
	public static void main(String[] args) {
		SpringApplication.run(AuthServiceApplication.class, args);
	}
}