package ru.lazarev.springcourse.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<Void> authorizationException(ServiceException ex) {
        log.error("exception: " + ex.getMessage());
        return new ResponseEntity<>(HttpStatus.valueOf(ex.getStatus()));
    }
}
