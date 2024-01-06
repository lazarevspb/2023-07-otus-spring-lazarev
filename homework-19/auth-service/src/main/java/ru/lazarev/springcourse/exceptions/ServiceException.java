package ru.lazarev.springcourse.exceptions;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@AllArgsConstructor
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ServiceException extends RuntimeException {
    int status;
    String message;
    Date timestamp;

    public ServiceException(int status, String message) {
        this.status = status;
        this.message = message;
        this.timestamp = new Date();
    }
}
