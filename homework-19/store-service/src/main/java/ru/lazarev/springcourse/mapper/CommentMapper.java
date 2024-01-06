package ru.lazarev.springcourse.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.lazarev.springcourse.domain.Author;
import ru.lazarev.springcourse.domain.Book;
import ru.lazarev.springcourse.domain.Comment;
import ru.lazarev.springcourse.dto.AuthorDto;
import ru.lazarev.springcourse.dto.CommentDto;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    @Mapping(target = "id", source = "commentDto.id")
    Comment map(CommentDto commentDto, Book book);
}
