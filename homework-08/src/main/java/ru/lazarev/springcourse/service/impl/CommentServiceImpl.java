package ru.lazarev.springcourse.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import ru.lazarev.springcourse.repository.CommentRepository;
import ru.lazarev.springcourse.domain.Comment;
import ru.lazarev.springcourse.dto.CommentDto;
import ru.lazarev.springcourse.mapper.CommentMapper;
import ru.lazarev.springcourse.service.BookService;
import ru.lazarev.springcourse.service.CommentService;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CommentServiceImpl implements CommentService {

    BookService bookService;
    CommentRepository repository;
    CommentMapper commentMapper;

    @Override
    public Comment findCommentById(String id) {
        return repository.findById(id).get();
    }

    @Override
    public void saveComment(CommentDto commentDto) {
        commentDto.setId(null);
        var book = bookService.findBookById(commentDto.getBookId());
        repository.save(commentMapper.map(commentDto, book));
    }

    @Override
    public void updateComment(CommentDto commentDto) {
        var book = bookService.findBookById(commentDto.getBookId());
        repository.save(commentMapper.map(commentDto, book));
    }

    @Override
    public void deleteCommentById(String id) {
        repository.deleteById(id);
    }
}
