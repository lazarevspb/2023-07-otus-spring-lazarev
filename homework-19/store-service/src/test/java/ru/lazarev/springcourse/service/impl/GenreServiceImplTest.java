package ru.lazarev.springcourse.service.impl;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.lazarev.springcourse.domain.Genre;
import ru.lazarev.springcourse.dto.GenreDto;
import ru.lazarev.springcourse.mapper.GenreMapper;
import ru.lazarev.springcourse.mapper.GenreMapperImpl;
import ru.lazarev.springcourse.repository.GenreRepository;
import ru.lazarev.springcourse.service.GenreService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith({SpringExtension.class})
@ContextConfiguration(classes = {GenreServiceImpl.class, GenreMapperImpl.class})
@FieldDefaults(level = AccessLevel.PRIVATE)
class GenreServiceImplTest {

    public static final String Genre_NAME = "Genre_name";

    public static final long Genre_ID = 1L;

    @MockBean
    GenreRepository repository;

    @Autowired
    GenreMapper mapper;

    @Autowired
    GenreService service;

    @Test
    void getAllGenre() {
        when(repository.findAll()).thenReturn(getGenreList());

        var actual = service.getAllGenre();

        assertEquals(getGenreDtoList(), actual);
    }

    private List<Genre> getGenreList() {
        return List.of(getGenre());
    }

    private Genre getGenre() {
        return new Genre(Genre_ID, Genre_NAME);
    }

    private List<GenreDto> getGenreDtoList() {
        return List.of(getGenreDto());
    }

    private GenreDto getGenreDto() {
        return new GenreDto(Genre_ID, Genre_NAME);
    }
}