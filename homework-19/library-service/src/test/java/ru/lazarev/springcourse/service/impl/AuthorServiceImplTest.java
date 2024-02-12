package ru.lazarev.springcourse.service.impl;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.Test;
import ru.lazarev.springcourse.library.service.impl.AuthorServiceImpl;
import ru.lazarev.springcourse.library.mapper.AuthorMapperImpl;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.lazarev.springcourse.library.domain.Author;
import ru.lazarev.springcourse.library.dto.AuthorDto;
import ru.lazarev.springcourse.library.mapper.AuthorMapper;
import ru.lazarev.springcourse.library.repository.AuthorRepository;
import ru.lazarev.springcourse.library.service.AuthorService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {AuthorServiceImpl.class, AuthorMapperImpl.class})
@ExtendWith({SpringExtension.class})
@FieldDefaults(level = AccessLevel.PRIVATE)
class AuthorServiceImplTest {

    public static final String AUTHOR_NAME = "Author_name";

    public static final long AUTHOR_ID = 1L;

    @MockBean
    AuthorRepository repository;

    @Autowired
    AuthorMapper mapper;

    @Autowired
    AuthorService service;

    @Test
    void getAllAuthor() {
        when(repository.findAll()).thenReturn(getAuthorList());

        var actual = service.getAllAuthor();

        assertEquals(getAuthorDtoList(), actual);
    }

    private List<Author> getAuthorList() {
        return List.of(getAuthor());
    }

    private Author getAuthor() {
        return new Author(AUTHOR_ID, AUTHOR_NAME);
    }

    private List<AuthorDto> getAuthorDtoList() {
        return List.of(getAuthorDto());
    }

    private AuthorDto getAuthorDto() {
        return new AuthorDto(AUTHOR_ID, AUTHOR_NAME);
    }
}