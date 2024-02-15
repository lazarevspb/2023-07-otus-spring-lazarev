package ru.lazarev.springcourse.library.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.lazarev.springcourse.library.dto.UserProfileDto;
import ru.lazarev.springcourse.library.model.UserProfile;

@Mapper(componentModel = "spring")
public interface UserProfileMapper {
    @Mapping(target = "userId", source = "id")
    UserProfile map(UserProfileDto userProfileDto);
}
