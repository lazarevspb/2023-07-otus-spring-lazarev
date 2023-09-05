package ru.lazarev.springcourse.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import ru.lazarev.springcourse.dao.AuthorDao;
import ru.lazarev.springcourse.dto.AuthorDto;
import ru.lazarev.springcourse.mapper.AuthorMapper;
import ru.lazarev.springcourse.service.AuthorService;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthorServiceImpl implements AuthorService {

    AuthorDao dao;

    AuthorMapper mapper;

    @Override
    public List<AuthorDto> getAllAuthor() {
        return dao.findAll().stream()
            .map(mapper::map)
            .toList();
    }

    @Override
    public AuthorDto findAuthorById(Long id) {
        return mapper.map(dao.findById(id));
    }
}
