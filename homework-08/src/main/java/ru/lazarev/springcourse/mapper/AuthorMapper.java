package ru.lazarev.springcourse.mapper;

import org.mapstruct.Mapper;
import ru.lazarev.springcourse.domain.Author;
import ru.lazarev.springcourse.dto.AuthorDto;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

    AuthorDto map(Author author);
}
