package ru.lazarev.springcourse.mapper;

import org.mapstruct.Mapper;
import ru.lazarev.springcourse.domain.Genre;
import ru.lazarev.springcourse.dto.GenreDto;

@Mapper(componentModel = "spring")
public interface GenreMapper {

    GenreDto map(Genre genre);
}
