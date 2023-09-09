package ru.lazarev.springcourse.dao;

import ru.lazarev.springcourse.domain.Genre;

import java.util.List;

public interface GenreDao {

    List<Genre> findAll();
}
