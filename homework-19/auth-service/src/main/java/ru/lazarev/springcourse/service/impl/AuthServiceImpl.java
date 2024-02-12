package ru.lazarev.springcourse.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import ru.lazarev.springcourse.AuditType;
import ru.lazarev.springcourse.dtos.JwtRequest;
import ru.lazarev.springcourse.dtos.RegistrationUserDto;
import ru.lazarev.springcourse.entities.User;
import ru.lazarev.springcourse.exceptions.ServiceException;
import ru.lazarev.springcourse.kafka.AuditKafkaProducer;
import ru.lazarev.springcourse.kafka.message.AuditKafkaMessage;
import ru.lazarev.springcourse.mapper.UserMapper;
import ru.lazarev.springcourse.service.AuthService;
import ru.lazarev.springcourse.service.UserService;
import ru.lazarev.springcourse.utils.JwtTokenUtils;

import java.time.Instant;
import java.util.UUID;

import static ru.lazarev.springcourse.AuditType.USER_LOGIN;
import static ru.lazarev.springcourse.AuditType.USER_REGISTER;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class AuthServiceImpl implements AuthService {
    UserService userService;
    JwtTokenUtils jwtTokenUtils;
    AuthenticationManager authenticationManager;
    AuditKafkaProducer auditKafkaProducer;
    UserMapper mapper;

    @Override
    public String createAuthToken(@RequestBody JwtRequest authRequest) {
        try {
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        } catch (BadCredentialsException e) {
            log.error(e.getMessage());
            throw new ServiceException(HttpStatus.UNAUTHORIZED.value(), "Incorrect login or password");
        }
        var userDetails = userService.loadUserByUsername(authRequest.getUsername());

        userService.findByUsername(authRequest.getUsername())
            .ifPresent(user -> auditKafkaProducer.publish(getAuditKafkaMessage(user.getId(), USER_LOGIN)));
        ;

        return jwtTokenUtils.generateToken(userDetails);
    }

    @Override
    public User createNewUser(@RequestBody RegistrationUserDto registrationUserDto) {
        if (!registrationUserDto.getPassword().equals(registrationUserDto.getConfirmPassword())) {
            throw new ServiceException(HttpStatus.BAD_REQUEST.value(), "Password mismatch");
        }
        if (userService.findByUsername(registrationUserDto.getUsername()).isPresent()) {
            throw new ServiceException(HttpStatus.BAD_REQUEST.value(), "A user with the specified name already exists");
        }
        var newUser = userService.createNewUser(registrationUserDto);

        auditKafkaProducer.publish(getAuditKafkaMessage(newUser.getId(), USER_REGISTER));
        return newUser;
    }

    private AuditKafkaMessage getAuditKafkaMessage(Long userId, AuditType eventType) {
        return AuditKafkaMessage.builder()
            .eventId(UUID.randomUUID().toString())
            .eventType(eventType)
            .timestamp(Instant.now().toEpochMilli())
            .message(AuditKafkaMessage.AuditEventMessage.builder()
                         .userId(userId)
                         .bookId(null)
                         .build()).build();
    }
}