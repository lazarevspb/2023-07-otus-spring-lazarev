package ru.lazarev.springcourse.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import ru.lazarev.springcourse.dao.BookDao;
import ru.lazarev.springcourse.domain.Book;

import java.util.List;

@RequiredArgsConstructor
@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookDaoJpa implements BookDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Book> findAll() {
        TypedQuery<Book> query = entityManager.createQuery(
            "SELECT DISTINCT b FROM Book b JOIN FETCH b.author JOIN FETCH b.genre ORDER BY b.id",
            Book.class);
        return query.getResultList();
    }

    @Override
    public Book findById(Long id) {
        String jpql =
            "SELECT b FROM Book b JOIN FETCH b.author JOIN FETCH b.genre g WHERE b.id = :id";
        TypedQuery<Book> query = entityManager.createQuery(jpql, Book.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public void save(Book book) {
        if (book.getId() == null) {
            entityManager.persist(book);
        } else {
            entityManager.merge(book);
        }
    }

    @Override
    public void delete(Long id) {
        Query queryDeleteComment = entityManager.createQuery("delete from Comment c where c.book.id = :id");
        Query queryDeleteBook = entityManager.createQuery("delete from Book b where b.id = :id");

        queryDeleteComment.setParameter("id", id);
        queryDeleteComment.executeUpdate();

        queryDeleteBook.setParameter("id", id);
        queryDeleteBook.executeUpdate();
    }
}
