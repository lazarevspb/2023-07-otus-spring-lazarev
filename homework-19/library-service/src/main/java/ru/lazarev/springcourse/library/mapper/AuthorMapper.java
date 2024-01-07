package ru.lazarev.springcourse.library.mapper;

import org.mapstruct.Mapper;
import ru.lazarev.springcourse.library.domain.Author;
import ru.lazarev.springcourse.library.dto.AuthorDto;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

    AuthorDto map(Author author);
}
