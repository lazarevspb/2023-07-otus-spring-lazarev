package ru.lazarev.springcourse.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import ru.lazarev.springcourse.repository.GenreRepository;
import ru.lazarev.springcourse.domain.Genre;
import ru.lazarev.springcourse.dto.GenreDto;
import ru.lazarev.springcourse.mapper.GenreMapper;
import ru.lazarev.springcourse.service.GenreService;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class GenreServiceImpl implements GenreService {

    GenreRepository repository;

    GenreMapper mapper;

    @Override
    public List<GenreDto> getAllGenre() {
        return repository.findAll()
            .stream()
            .map(mapper::map)
            .toList();
    }

    @Override
    public Genre findGenreById(Long id) {
        return repository.findById(id).orElseThrow(RuntimeException::new);
    }
}