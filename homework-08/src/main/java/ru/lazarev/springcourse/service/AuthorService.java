package ru.lazarev.springcourse.service;

import ru.lazarev.springcourse.domain.Author;
import ru.lazarev.springcourse.dto.AuthorDto;

import java.util.List;

public interface AuthorService {

    List<AuthorDto> getAllAuthor();

    Author findAuthorById(Long id);
}
