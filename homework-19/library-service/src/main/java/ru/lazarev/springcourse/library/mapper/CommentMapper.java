package ru.lazarev.springcourse.library.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.lazarev.springcourse.library.domain.Book;
import ru.lazarev.springcourse.library.domain.Comment;
import ru.lazarev.springcourse.library.dto.CommentDto;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    @Mapping(target = "id", source = "commentDto.id")
    Comment map(CommentDto commentDto, Book book);
}
