package ru.lazarev.springcourse.library.service;

import ru.lazarev.springcourse.library.domain.Author;
import ru.lazarev.springcourse.library.dto.AuthorDto;

import java.util.List;

public interface AuthorService {

    List<AuthorDto> getAllAuthor();

    Author findAuthorById(Long id);

    Author findByName(String name);
}
