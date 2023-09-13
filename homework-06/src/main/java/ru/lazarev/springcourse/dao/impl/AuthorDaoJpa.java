package ru.lazarev.springcourse.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import ru.lazarev.springcourse.dao.AuthorDao;
import ru.lazarev.springcourse.domain.Author;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthorDaoJpa implements AuthorDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Author> findAll() {
        TypedQuery<Author> query = entityManager.createQuery("select a from Author a ", Author.class);
        return query.getResultList();
    }

    @Override
    public Optional<Author> findById(Long id) {
        return Optional.of(entityManager.find(Author.class, id));
    }
}
