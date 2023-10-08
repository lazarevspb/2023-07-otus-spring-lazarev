package ru.lazarev.springcourse.controller;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.lazarev.springcourse.domain.Book;
import ru.lazarev.springcourse.mapper.BookMapper;
import ru.lazarev.springcourse.mapper.BookMapperImpl;
import ru.lazarev.springcourse.service.AuthorService;
import ru.lazarev.springcourse.service.BookService;
import ru.lazarev.springcourse.service.GenreService;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest
@Import(BookController.class)
@ContextConfiguration(classes = {BookMapperImpl.class})
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @Spy
    private BookMapper bookMapper = Mappers.getMapper(BookMapper.class);

    @MockBean
    private AuthorService authorService;

    @MockBean
    private GenreService genreService;

    @Test
    void getAllBooks() throws Exception {
        List<Book> books = Collections.singletonList(new Book());

        when(bookService.findAllBooks()).thenReturn(books);

        mockMvc.perform(MockMvcRequestBuilders.get("/books"))
            .andDo(print())
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.view().name("index"))
            .andExpect(MockMvcResultMatchers.model().attributeExists("books"));
    }

    @Test
    void editBooks() throws Exception {
        long bookId = 1L;
        when(bookService.findBookById(bookId)).thenReturn(new Book());

        mockMvc.perform(MockMvcRequestBuilders.get("/books/edit?id=" + bookId))
            .andDo(print())
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.view().name("edit"))
            .andExpect(MockMvcResultMatchers.model().attributeExists("authors"))
            .andExpect(MockMvcResultMatchers.model().attributeExists("genres"))
            .andExpect(MockMvcResultMatchers.model().attributeExists("book"));

    }

    @Test
    void updateBook() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/books/edit")
                            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                            .param("title", "Book Title")
                            .param("author", "Author Name")
                            .param("genre", "Genre")
                            .param("id", "1"))
            .andDo(print())
            .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
            .andExpect(MockMvcResultMatchers.redirectedUrl("/books"));
    }

    @Test
    public void deleteBook() throws Exception {
        long bookId = 1L;
        doNothing().when(bookService).deleteBookById(bookId);

        mockMvc.perform(MockMvcRequestBuilders.delete("/books/{id}", bookId))
            .andDo(print())
            .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
            .andExpect(MockMvcResultMatchers.redirectedUrl("/books"));
    }

    @Test
    public void newBook() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/books/new"))
            .andDo(print())
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.view().name("new-book-form"))
            .andExpect(MockMvcResultMatchers.model().attributeExists("authors"))
            .andExpect(MockMvcResultMatchers.model().attributeExists("genres"))
            .andExpect(MockMvcResultMatchers.model().attributeExists("book"));
    }

    @Test
    public void testSaveNewBook() throws Exception {
        doNothing().when(bookService).saveBook(any());

        mockMvc.perform(MockMvcRequestBuilders.post("/books/save")
                            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                            .param("title", "Book Title")
                            .param("author", "Author Name")
                            .param("genre", "Genre"))
            .andDo(print())
            .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
            .andExpect(MockMvcResultMatchers.redirectedUrl("/books"));
    }
}