package ru.lazarev.springcourse.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import ru.lazarev.springcourse.dao.GenreDao;
import ru.lazarev.springcourse.dto.GenreDto;
import ru.lazarev.springcourse.mapper.GenreMapper;
import ru.lazarev.springcourse.service.GenreService;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class GenreServiceImpl implements GenreService {

    GenreDao dao;

    GenreMapper mapper;

    @Override
    public List<GenreDto> getAllGenre() {
        return dao.findAll()
            .stream()
            .map(mapper::map)
            .toList();
    }

    @Override
    public GenreDto findGenreById(Long id) {
        return mapper.map(dao.findById(id));
    }
}
