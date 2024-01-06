package ru.lazarev.springcourse.mapper;

import org.mapstruct.Mapper;
import ru.lazarev.springcourse.dtos.UserDto;
import ru.lazarev.springcourse.entities.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto map(User user);
}
