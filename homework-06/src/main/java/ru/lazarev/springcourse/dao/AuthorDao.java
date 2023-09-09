package ru.lazarev.springcourse.dao;

import ru.lazarev.springcourse.domain.Author;

import java.util.List;

public interface AuthorDao {

    List<Author> findAll();
}
