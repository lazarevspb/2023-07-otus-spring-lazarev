package ru.lazarev.springcourse.dtos;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import ru.lazarev.springcourse.entities.Role;

import java.util.Collection;

@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDto {
    Long id;
    String username;
    String email;
    Collection<RoleDto> roles;
}
