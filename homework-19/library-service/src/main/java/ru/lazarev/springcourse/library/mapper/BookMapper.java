package ru.lazarev.springcourse.library.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.lazarev.springcourse.library.domain.Book;
import ru.lazarev.springcourse.library.dto.BookDto;

@Mapper(componentModel = "spring")
public interface BookMapper {

    @Mapping(target = "title", source = "title")
    @Mapping(target = "author", source = "author.name")
    @Mapping(target = "genre", source = "genre.name")
    BookDto map(Book book);
}
