package ru.lazarev.springcourse.library.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import ru.lazarev.springcourse.library.dto.RoleDto;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserProfile {
    Long userId;
    String username;
    String email;
    List<RoleDto> roles;
}
