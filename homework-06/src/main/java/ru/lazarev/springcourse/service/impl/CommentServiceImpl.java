package ru.lazarev.springcourse.service.impl;

import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import ru.lazarev.springcourse.dao.CommentDao;
import ru.lazarev.springcourse.domain.Comment;
import ru.lazarev.springcourse.dto.CommentDto;
import ru.lazarev.springcourse.service.CommentService;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CommentServiceImpl implements CommentService {

    CommentDao commentDao;

    @Override
    public List<Comment> findAllCommentsByBookId(Long id) {
        return commentDao.findAllCommentByBookId(id);
    }

    @Override
    public Comment findCommentById(Long id) {
        return commentDao.findById(id);
    }

    @Override
    @Transactional
    public void saveComment(CommentDto comment) {
        comment.setId(null);
        commentDao.save(comment);
    }

    @Override
    @Transactional
    public void updateComment(CommentDto comment) {
        commentDao.update(comment);
    }

    @Override
    @Transactional
    public void deleteCommentById(Long id) {
        commentDao.delete(id);
    }
}
