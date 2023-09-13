package ru.lazarev.springcourse.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import ru.lazarev.springcourse.dao.CommentDao;
import ru.lazarev.springcourse.domain.Comment;
import ru.lazarev.springcourse.dto.CommentDto;

@RequiredArgsConstructor
@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CommentDaoJpa implements CommentDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Comment findById(Long id) {
        return entityManager.find(Comment.class, id);
    }

    @Override
    public void save(CommentDto comment) {
        if (comment.getId() == null) {
            entityManager.persist(comment);
        } else {
            entityManager.merge(comment);
        }
    }

    @Override
    public void update(CommentDto comment) {
        var commentInDb = entityManager.find(Comment.class, comment.getId());
        commentInDb.setText(comment.getText());
        entityManager.merge(commentInDb);
    }

    @Override
    public void delete(Long id) {
        entityManager.remove(entityManager.find(Comment.class, id));
    }
}
