package ru.lazarev.springcourse.library.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import ru.lazarev.springcourse.library.domain.Comment;
import ru.lazarev.springcourse.library.dto.CommentDto;
import ru.lazarev.springcourse.library.feign.AuthServiceProxy;
import ru.lazarev.springcourse.library.mapper.CommentMapper;
import ru.lazarev.springcourse.library.repository.CommentRepository;
import ru.lazarev.springcourse.library.service.BookService;
import ru.lazarev.springcourse.library.service.CommentService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CommentServiceImpl implements CommentService {

    BookService bookService;
    CommentRepository repository;
    CommentMapper commentMapper;
    AuthServiceProxy authServiceProxy;

    @Override
    public Comment findCommentById(Long id) {
        return repository.findById(id).get();
    }

    @Override
    public List<CommentDto> findCommentsByBookId(Long id) {
        return repository.findCommentsByBookId(id).stream()
            .map(comment -> commentMapper.map(comment, authServiceProxy.getUserById(comment.getUserId()).getUsername()))
            .collect(
                Collectors.toList());
    }

    @Override
    public void saveComment(CommentDto commentDto) {
        commentDto.setId(null);
        var book = bookService.findBookById(commentDto.getBookId(), null);
        repository.save(commentMapper.map(commentDto, book));
    }

    @Override
    public void updateComment(CommentDto commentDto) {
        var book = bookService.findBookById(commentDto.getBookId(), null);
        repository.save(commentMapper.map(commentDto, book));
    }

    @Override
    public void deleteCommentById(Long id) {
        repository.deleteById(id);
    }
}
