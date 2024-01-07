package ru.lazarev.springcourse.library.service;

import ru.lazarev.springcourse.library.domain.Genre;
import ru.lazarev.springcourse.library.dto.GenreDto;

import java.util.List;

public interface GenreService {

    List<GenreDto> getAllGenre();

    Genre findGenreById(Long id);

    Genre findByName(String name);
}
