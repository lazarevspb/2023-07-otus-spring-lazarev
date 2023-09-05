package ru.lazarev.springcourse.service;

import ru.lazarev.springcourse.dto.GenreDto;

import java.util.List;

public interface GenreService {

    List<GenreDto> getAllGenre();

    GenreDto findGenreById(Long id);
}
