package ru.lazarev.springcourse.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import ru.lazarev.springcourse.mapper.BookMapperImpl;
import ru.lazarev.springcourse.repository.BookRepository;
import ru.lazarev.springcourse.service.AuthorService;
import ru.lazarev.springcourse.service.BookService;
import ru.lazarev.springcourse.service.GenreService;
import ru.lazarev.springcourse.service.impl.BookServiceImpl;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(BookController.class)
@Import({BookController.class, BookMapperImpl.class, BookServiceImpl.class})
public class SecurityControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    BookRepository repository;
    @MockBean
    AuthorService authorService;
    @MockBean
    GenreService genreService;

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

    @Test
    public void should_get_unauthorized_status_if_unauthorized_request_on_books_page() throws Exception {
        mockMvc.perform(get("/books"))
            .andExpect(status().isUnauthorized());
    }

    @Test
    public void should_get_forbidden_status_if_unauthorized_request_on_edit_page() throws Exception {
        mockMvc.perform(post("/books/edit?id=2"))
            .andExpect(status().isForbidden());
    }

    @Test
    public void should_get_unauthorized_status_if_unauthorized_request_on_new_page() throws Exception {
        mockMvc.perform(get("/books/new"))
            .andExpect(status().isUnauthorized());
    }

    @Test
    public void should_get_forbidden_status_if_unauthorized_request_on_save_page() throws Exception {
        mockMvc.perform(post("/books/save"))
            .andExpect(status().isForbidden());
    }

    @Test
    public void should_get_forbidden_status_if_unauthorized_request_on_delete_page() throws Exception {
        mockMvc.perform(delete("/books/5"))
            .andExpect(status().isForbidden());
    }
}
