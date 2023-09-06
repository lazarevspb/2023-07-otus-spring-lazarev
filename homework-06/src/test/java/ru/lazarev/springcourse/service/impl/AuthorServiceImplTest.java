package ru.lazarev.springcourse.service.impl;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.lazarev.springcourse.dao.AuthorDao;
import ru.lazarev.springcourse.domain.Author;
import ru.lazarev.springcourse.dto.AuthorDto;
import ru.lazarev.springcourse.mapper.AuthorMapper;
import ru.lazarev.springcourse.mapper.AuthorMapperImpl;
import ru.lazarev.springcourse.service.AuthorService;

import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {AuthorServiceImpl.class, AuthorMapperImpl.class})
@ExtendWith({SpringExtension.class})
@FieldDefaults(level = AccessLevel.PRIVATE)
class AuthorServiceImplTest {

    public static final String AUTHOR_NAME = "Author_name";

    public static final long AUTHOR_ID = 1L;

    @MockBean
    AuthorDao dao;

    @Autowired
    AuthorMapper mapper;

    @Autowired
    AuthorService service;

    @Test
    void getAllAuthor() {
        when(dao.findAll()).thenReturn(getAuthorList());

        var actual = service.getAllAuthor();

        assertEquals(getAuthorDtoList(), actual);
    }

    @Test
    void findAuthorById() {
        when(dao.findById(eq(1L))).thenReturn(getAuthor());

        var actual = service.findAuthorById(1L);

        assertEquals(getAuthorDto(), actual);
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