package ru.lazarev.springcourse.library.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import ru.lazarev.springcourse.library.service.BookService;
import ru.lazarev.springcourse.library.service.CommentService;
import ru.lazarev.springcourse.library.repository.CommentRepository;
import ru.lazarev.springcourse.library.domain.Comment;
import ru.lazarev.springcourse.library.dto.CommentDto;
import ru.lazarev.springcourse.library.mapper.CommentMapper;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CommentServiceImpl implements CommentService {

    BookService bookService;
    CommentRepository repository;
    CommentMapper commentMapper;

    @Override
    public Comment findCommentById(Long id) {
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
    public void deleteCommentById(Long id) {
        repository.deleteById(id);
    }
}
