package ru.lazarev.springcourse.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
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
        TypedQuery<Book> query = entityManager.createQuery("SELECT DISTINCT b FROM Book b", Book.class);
        return query.getResultList();
    }

    @Override
    public Book findById(Long id) {
        return entityManager.find(Book.class, id);
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
    public void delete(Book book) {
        entityManager.remove(book);
    }
}
