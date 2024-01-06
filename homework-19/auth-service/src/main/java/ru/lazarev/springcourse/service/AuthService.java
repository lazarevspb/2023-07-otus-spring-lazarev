package ru.lazarev.springcourse.service;

import org.springframework.web.bind.annotation.RequestBody;
import ru.lazarev.springcourse.dtos.JwtRequest;
import ru.lazarev.springcourse.dtos.RegistrationUserDto;
import ru.lazarev.springcourse.entities.User;

public interface AuthService {
    String createAuthToken(@RequestBody JwtRequest authRequest);

    User createNewUser(@RequestBody RegistrationUserDto registrationUserDto);
}
