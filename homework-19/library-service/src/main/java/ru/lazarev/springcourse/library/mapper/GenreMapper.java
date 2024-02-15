package ru.lazarev.springcourse.library.mapper;

import org.mapstruct.Mapper;
import ru.lazarev.springcourse.library.domain.Genre;
import ru.lazarev.springcourse.library.dto.GenreDto;

@Mapper(componentModel = "spring")
public interface GenreMapper {

    GenreDto map(Genre genre);
}
