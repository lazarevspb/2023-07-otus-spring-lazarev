package ru.lazarev.springcourse.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.lazarev.springcourse.library.controller.BookController;
import ru.lazarev.springcourse.library.domain.Author;
import ru.lazarev.springcourse.library.domain.Book;
import ru.lazarev.springcourse.library.domain.Genre;
import ru.lazarev.springcourse.library.dto.BookDto;
import ru.lazarev.springcourse.library.mapper.BookMapper;
import ru.lazarev.springcourse.library.mapper.BookMapperImpl;
import ru.lazarev.springcourse.library.service.BookService;

import java.util.List;

import static java.util.Collections.emptyList;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {BookController.class, BookMapperImpl.class})
@ExtendWith(SpringExtension.class)
public class BookControllerTest {

    public static final String TITLE_1 = "title_1";
    public static final String AUTHOR_NAME_1 = "author_name_1";
    public static final String GENRE_NAME_1 = "genre_name_1";
    public static final String TITLE_0 = "title_0";
    public static final String AUTHOR_NAME_0 = "author_name_0";
    public static final String GENRE_NAME_0 = "genre_name_0";
    public static final long BOOK_ID_1 = 1L;
    public static final long BOOK_ID_0 = 0L;
    @MockBean
    BookService bookService;
    @Autowired
    BookMapper bookMapper;

    @Autowired
    BookController controller;

    @Test
    void get_all_book_test() throws Exception {
        var expectedList = List.of(getBook0Dto(), getBook1Dto());
        var bookList = List.of(getBook0(), getBook1());
        when(bookService.findAllBooks(anyLong())).thenReturn(bookList);

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/books")
            .contentType(MediaType.APPLICATION_JSON);

        MockMvcBuilders.standaloneSetup(controller).build().perform(requestBuilder)
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.content().string(new ObjectMapper().writeValueAsString(expectedList)));

        verify(bookService).findAllBooks(anyLong());
    }

    @Test
    void get_book_by_id_test() throws Exception {

        when(bookService.findBookById(anyLong(), anyLong())).thenReturn(getBook1());

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/books/{id}", 1L)
            .contentType(MediaType.APPLICATION_JSON);

        MockMvcBuilders.standaloneSetup(controller).build().perform(requestBuilder)
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.content().string(new ObjectMapper().writeValueAsString(getBook1Dto())));

        verify(bookService).findBookById(1L, anyLong());
    }

    @Test
    void delete_book_test() throws Exception {

        when(bookService.findBookById(anyLong(), anyLong())).thenReturn(getBook1());

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/v1/books/{id}", 1L)
            .contentType(MediaType.APPLICATION_JSON);

        MockMvcBuilders.standaloneSetup(controller).build().perform(requestBuilder)
            .andExpect(MockMvcResultMatchers.status().isNoContent());

        verify(bookService).deleteBookById(1l, anyLong());
    }

    @Test
    void update_book_test() throws Exception {
        when(bookService.findBookById(BOOK_ID_1, anyLong())).thenReturn(getBook1());

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/v1/books")
            .contentType(MediaType.APPLICATION_JSON)
            .content(new ObjectMapper().writeValueAsString(getBook1Dto()));

        MockMvcBuilders.standaloneSetup(controller).build().perform(requestBuilder)
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.content().string(new ObjectMapper().writeValueAsString(getBook1Dto())));

        verify(bookService).updateBook(getBook1Dto(), anyLong());
    }

    @Test
    void save_book_test() throws Exception {
        when(bookService.saveBook(getBook1Dto(), anyLong())).thenReturn(getBook1());

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1/books")
            .contentType(MediaType.APPLICATION_JSON)
            .content(new ObjectMapper().writeValueAsString(getBook1Dto()));

        MockMvcBuilders.standaloneSetup(controller).build().perform(requestBuilder)
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.content().string(new ObjectMapper().writeValueAsString(getBook1Dto())));

        verify(bookService).saveBook(getBook1Dto(), anyLong());
    }

    private Book getBook0() {
        return new Book(BOOK_ID_0, TITLE_0, new Author(1L, AUTHOR_NAME_0), new Genre(1L, GENRE_NAME_0), emptyList());
    }

    private Book getBook1() {
        return new Book(BOOK_ID_1, TITLE_1, new Author(2L, AUTHOR_NAME_1), new Genre(1L, GENRE_NAME_1), emptyList());
    }

    private BookDto getBook1Dto() {
        return new BookDto(BOOK_ID_1, TITLE_1, AUTHOR_NAME_1, GENRE_NAME_1);
    }

    private BookDto getBook0Dto() {
        return new BookDto(BOOK_ID_0, TITLE_0, AUTHOR_NAME_0, GENRE_NAME_0);
    }
}