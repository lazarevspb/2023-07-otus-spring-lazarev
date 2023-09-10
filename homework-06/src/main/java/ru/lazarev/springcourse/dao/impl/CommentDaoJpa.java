package ru.lazarev.springcourse.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import ru.lazarev.springcourse.dao.CommentDao;
import ru.lazarev.springcourse.domain.Comment;
import ru.lazarev.springcourse.dto.CommentDto;

import java.util.List;

@RequiredArgsConstructor
@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CommentDaoJpa implements CommentDao {

    @PersistenceContext
    private EntityManager entityManager;

        public List<Comment> findAllCommentByBookId(Long id) {
            TypedQuery<Comment> query =
                entityManager.createQuery("SELECT c FROM Comment c JOIN FETCH c.book WHERE c.book.id = :id", Comment.class);
            query.setParameter("id", id);
            return query.getResultList();
        }

    @Override
    public Comment findById(Long id) {
        String jpql =
            "SELECT c FROM Comment c JOIN FETCH c.book WHERE c.id = :id";
        TypedQuery<Comment> query = entityManager.createQuery(jpql, Comment.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public void save(CommentDto comment) {
        var query = entityManager.createQuery("INSERT INTO Comment(text, book) SELECT :text, b FROM Book b WHERE b.id = :book_id");
        query.setParameter("book_id", comment.getBookId());
        query.setParameter("text", comment.getText());
        query.executeUpdate();
    }

    @Override
    public void update(CommentDto comment) {
        var commentInDb = entityManager.find(Comment.class, comment.getId());
        commentInDb.setText(comment.getText());
        entityManager.merge(commentInDb);
    }

    @Override
    public void delete(Long id) {
        Query query = entityManager.createQuery("delete from Comment c where c.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }
}
