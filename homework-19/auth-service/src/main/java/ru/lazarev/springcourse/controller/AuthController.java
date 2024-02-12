package ru.lazarev.springcourse.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.lazarev.springcourse.dtos.JwtRequest;
import ru.lazarev.springcourse.dtos.JwtResponse;
import ru.lazarev.springcourse.dtos.RegistrationUserDto;
import ru.lazarev.springcourse.dtos.TokenVerifyRequest;
import ru.lazarev.springcourse.mapper.UserMapper;
import ru.lazarev.springcourse.service.AuthService;
import ru.lazarev.springcourse.service.UserService;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthController {
    AuthService authService;
    UserService userService;
    UserMapper mapper;

    @PostMapping("/")
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest) {
        return ResponseEntity.ok(new JwtResponse(authService.createAuthToken(authRequest)));
    }

    @PostMapping("/registration")
    public ResponseEntity<?> createNewUser(@RequestBody RegistrationUserDto registrationUserDto) {
        return ResponseEntity.ok(mapper.map(authService.createNewUser(registrationUserDto)));
    }

    @PostMapping("/verify")
    public ResponseEntity<?> getUserByToken(@RequestBody TokenVerifyRequest tokenVerifyRequest) {
        return ResponseEntity.ok(mapper.map(userService.getUserByToken(tokenVerifyRequest.getToken())));
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> getUserById(@PathVariable("id") Long userId) {
        return userService.findById(userId).map(user -> ResponseEntity.ok(mapper.map(user)))
            .orElse(ResponseEntity.notFound().build());
    }
}