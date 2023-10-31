package ru.lazarev.springcourse.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.lazarev.springcourse.dto.BookDto;
import ru.lazarev.springcourse.repository.AuthorRepository;
import ru.lazarev.springcourse.repository.BookRepository;
import ru.lazarev.springcourse.repository.CommentRepository;
import ru.lazarev.springcourse.repository.GenreRepository;
import ru.lazarev.springcourse.service.BookService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@WebFluxTest(BookController.class)
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
    @MockBean
    AuthorRepository authorRepository;
    @MockBean
    GenreRepository genreRepository;
    @MockBean
    BookRepository bookRepository;
    @MockBean
    CommentRepository commentRepository;

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    BookController controller;

    @Test
    void findAllBooksTest() {
        var bookDto = getBook1Dto();
        when(bookService.findAllBooks()).thenReturn(Flux.just(getBook0Dto(), bookDto));

        webTestClient.get().uri("/api/v1/books").accept(MediaType.APPLICATION_JSON).exchange().expectStatus().isOk()
            .expectBodyList(BookDto.class).hasSize(2).contains(getBook0Dto(), bookDto);

        verify(bookService).findAllBooks();
    }

    @Test
    void findBookByIdTest() {
        var bookDto = getBook1Dto();
        when(bookService.findBookById(anyLong())).thenReturn(Mono.just(bookDto));

        webTestClient.get().uri("/api/v1/books/{id}", BOOK_ID_1).accept(MediaType.APPLICATION_JSON).exchange()
            .expectStatus().isOk().expectBody(BookDto.class).isEqualTo(bookDto);

        verify(bookService).findBookById(BOOK_ID_1);
    }

    @Test
    void deleteBookByIdTest() {
        var bookDto = getBook1Dto();
        when(bookService.findBookById(anyLong())).thenReturn(Mono.just(bookDto));
        when(bookService.deleteBookById(anyLong())).thenReturn(Mono.empty());

        webTestClient.delete().uri("/api/v1/books/{id}", BOOK_ID_1).accept(MediaType.APPLICATION_JSON).exchange()
            .expectStatus().isNoContent();

        verify(bookService).deleteBookById(BOOK_ID_1);
    }

    @Test
    void deleteBookByIdIfNotFoundTest() {
        when(bookService.findBookById(anyLong())).thenReturn(Mono.empty());
        when(bookService.deleteBookById(anyLong())).thenReturn(Mono.empty());

        webTestClient.delete().uri("/api/v1/books/{id}", BOOK_ID_1).accept(MediaType.APPLICATION_JSON).exchange()
            .expectStatus().isNotFound();

        verify(bookService, never()).deleteBookById(BOOK_ID_1);
    }

    @Test
    void updateBookTest() {
        var bookDto = getBook1Dto();
        when(bookService.findBookById(anyLong())).thenReturn(Mono.just(bookDto));
        when(bookService.save(any())).thenReturn(Mono.just(bookDto));

        webTestClient.put().uri("/api/v1/books").bodyValue(bookDto).accept(MediaType.APPLICATION_JSON).exchange()
            .expectStatus().isOk();

        verify(bookService).save(bookDto);
    }

    @Test
    void updateBookTestIfNotFound() {
        when(bookService.findBookById(anyLong())).thenReturn(Mono.empty());
        var bookDto = getBook1Dto();

        webTestClient.put().uri("/api/v1/books").bodyValue(bookDto).accept(MediaType.APPLICATION_JSON).exchange()
            .expectStatus().isNotFound();

        verify(bookService, never()).save(bookDto);
    }

    @Test
    void saveBookTest() {
        var bookDto = getBook1Dto();
        when(bookService.save(any())).thenReturn(Mono.just(bookDto));

        webTestClient.post().uri("/api/v1/books").bodyValue(bookDto).accept(MediaType.APPLICATION_JSON).exchange()
            .expectStatus().isOk().expectBody(BookDto.class).isEqualTo(bookDto);

        verify(bookService).save(bookDto);
    }

    private BookDto getBook1Dto() {
        return new BookDto(BOOK_ID_1, TITLE_1, AUTHOR_NAME_1, GENRE_NAME_1);
    }

    private BookDto getBook0Dto() {
        return new BookDto(BOOK_ID_0, TITLE_0, AUTHOR_NAME_0, GENRE_NAME_0);
    }
}