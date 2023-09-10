package ru.lazarev.springcourse.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.lazarev.springcourse.domain.Book;
import ru.lazarev.springcourse.dto.AuthorDto;
import ru.lazarev.springcourse.dto.BookDto;
import ru.lazarev.springcourse.dto.GenreDto;

@Mapper(componentModel = "spring")
public interface BookMapper {

    @Mapping(target = "id", source = "book.id")
    @Mapping(target = "title", source = "book.title")
    BookDto map(Book book, AuthorDto author, GenreDto genre);

    BookDto map(Book book);
}
