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
import java.util.stream.Collectors;

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
            .collect(Collectors.toList());
    }

    @Override
    public Genre findGenreById(Long id) {
        return repository.findById(id).orElseThrow(RuntimeException::new);
    }

    @Override
    public Genre findByName(String name) {
        return repository.findByName(name).orElseThrow(RuntimeException::new);
    }
}
