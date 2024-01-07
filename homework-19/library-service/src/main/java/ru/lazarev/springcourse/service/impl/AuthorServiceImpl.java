package ru.lazarev.springcourse.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import ru.lazarev.springcourse.domain.Author;
import ru.lazarev.springcourse.dto.AuthorDto;
import ru.lazarev.springcourse.mapper.AuthorMapper;
import ru.lazarev.springcourse.repository.AuthorRepository;
import ru.lazarev.springcourse.service.AuthorService;

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
