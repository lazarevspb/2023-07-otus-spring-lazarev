package ru.lazarev.springcourse.mapper;

import org.mapstruct.Mapper;
import ru.lazarev.springcourse.domain.Content;
import ru.lazarev.springcourse.dto.ContentDto;

@Mapper(componentModel = "spring")
public interface ContentMapper {

    ContentDto map(Content content);
}
