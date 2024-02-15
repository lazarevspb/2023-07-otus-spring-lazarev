package ru.lazarev.springcourse.library.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import ru.lazarev.springcourse.library.domain.Author;
import ru.lazarev.springcourse.library.dto.AuthorDto;
import ru.lazarev.springcourse.library.service.AuthorService;
import ru.lazarev.springcourse.library.mapper.AuthorMapper;
import ru.lazarev.springcourse.library.repository.AuthorRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthorServiceImpl implements AuthorService {

    AuthorRepository repository;

    AuthorMapper mapper;

    @Override
    public List<AuthorDto> getAllAuthor() {
        return repository.findAll().stream()
            .map(mapper::map)
            .collect(Collectors.toList());
    }

    @Override
    public Author findAuthorById(Long id) {
        return repository.findById(id).orElseThrow(RuntimeException::new);
    }

    @Override
    public Author findByName(String name) {
        return repository.findByName(name).orElseThrow(RuntimeException::new);
    }
}
