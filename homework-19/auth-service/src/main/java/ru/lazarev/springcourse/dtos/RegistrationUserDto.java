package ru.lazarev.springcourse.dtos;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegistrationUserDto {
    String username;
    String password;
    String confirmPassword;
    String email;
}
