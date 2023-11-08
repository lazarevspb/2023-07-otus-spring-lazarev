package ru.lazarev.springcourse.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.lazarev.springcourse.dto.BookDto;
import ru.lazarev.springcourse.mapper.BookMapper;
import ru.lazarev.springcourse.repository.BookRepository;
import ru.lazarev.springcourse.repository.UserRepository;
import ru.lazarev.springcourse.security.SecurityConfig;
import ru.lazarev.springcourse.service.AuthorService;
import ru.lazarev.springcourse.service.BookService;
import ru.lazarev.springcourse.service.GenreService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class)
@Import(SecurityConfig.class)
public class BookControllerSecurityTest {

    @MockBean
    BookRepository repository;
    @MockBean
    AuthorService authorService;
    @MockBean
    UserRepository userRepository;
    @MockBean
    BookService bookService;

    @MockBean
    BookMapper bookMapper;

    @MockBean
    InMemoryUserDetailsManager inMemoryUserDetailsManager;
    @MockBean
    GenreService genreService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void should_return_ok_when_books_with_role_admin() throws Exception {
        mockMvc.perform(get("/books"))
            .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin", roles = {"UNKNOWN"})
    public void should_return_forbidden_when_books_with_role_unknown() throws Exception {
        mockMvc.perform(get("/books"))
            .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void should_return_ok_when_books_with_role_user() throws Exception {
        mockMvc.perform(get("/books"))
            .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void should_return_forbidden_when_books_edit_with_role_user() throws Exception {
        mockMvc.perform(get("/books/edit?id=1"))
            .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = "user", roles = {"ADMIN"})
    public void should_return_ok_when_books_edit_with_role_admin() throws Exception {
        when(bookMapper.map(any())).thenReturn(getBookDto());
        mockMvc.perform(get("/books/edit?id=1"))
            .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "user", roles = {"ADMIN"})
    public void should_return_ok_when_books_new_with_role_admin() throws Exception {
        mockMvc.perform(get("/books/new"))
            .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void should_return_forbidden_when_books_new_with_role_user() throws Exception {
        mockMvc.perform(get("/books/new"))
            .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void should_return_forbidden_when_books_save_with_role_user() throws Exception {
        mockMvc.perform(post("/books/save"))
            .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = "user", roles = {"ADMIN"})
    public void should_return_ok_when_books_save_with_role_admin() throws Exception {
        mockMvc.perform(post("/books/save"))
            .andExpect(status().is3xxRedirection());
    }

    @Test
    public void should_login_page_must_be_available_without_authentication() throws Exception {
        mockMvc.perform(get("/login"))
            .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin", password = "1234", authorities = {"ROLE_ADMIN"})
    public void test_authenticated_on() throws Exception {
        mockMvc.perform(get("/books"))
            .andExpect(status().isOk());
    }

    private BookDto getBookDto() {
        var book = new BookDto();
        book.setId(1L);
        book.setTitle("title");
        book.setAuthor("author");
        book.setGenre("genre");
        return book;
    }
}
