package ru.lazarev.springcourse.service.impl;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.lazarev.springcourse.library.domain.Author;
import ru.lazarev.springcourse.library.domain.Book;
import ru.lazarev.springcourse.library.domain.Comment;
import ru.lazarev.springcourse.library.domain.Genre;
import ru.lazarev.springcourse.library.dto.BookDto;
import ru.lazarev.springcourse.library.enums.AuditType;
import ru.lazarev.springcourse.library.kafka.AuditKafkaProducer;
import ru.lazarev.springcourse.library.kafka.message.AuditKafkaMessage;
import ru.lazarev.springcourse.library.mapper.AuthorMapperImpl;
import ru.lazarev.springcourse.library.mapper.GenreMapperImpl;
import ru.lazarev.springcourse.library.repository.BookRepository;
import ru.lazarev.springcourse.library.service.AuthorService;
import ru.lazarev.springcourse.library.service.BookService;
import ru.lazarev.springcourse.library.service.GenreService;
import ru.lazarev.springcourse.library.service.impl.BookServiceImpl;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static ru.lazarev.springcourse.library.enums.AuditType.DELETE_BOOK;
import static ru.lazarev.springcourse.library.enums.AuditType.GET_BOOK;
import static ru.lazarev.springcourse.library.enums.AuditType.GET_LIST_BOOKS;
import static ru.lazarev.springcourse.library.enums.AuditType.SAVE_BOOK;
import static ru.lazarev.springcourse.library.enums.AuditType.UPDATE_BOOK;

@ExtendWith({SpringExtension.class})
@ContextConfiguration(classes = {BookServiceImpl.class, AuthorMapperImpl.class, GenreMapperImpl.class})
@FieldDefaults(level = AccessLevel.PRIVATE)
class BookServiceImplTest {

    public static final String Book_NAME = "Book_name";
    public static final long BOOK_ID = 1L;
    public static final String AUTHOR_1 = "Author 1";
    public static final String GENRE_2 = "Genre 2";
    public static final String AUTHOR_NAME = "Author_name";
    public static final String GENRE_NAME = "Genre_name";
    public static final Long GENRE_ID = 2L;
    public static final Long AUTHOR_ID = 1L;
    public static final long USER_ID = 111L;

    @MockBean
    BookRepository repository;

    @MockBean
    AuthorService authorService;

    @MockBean
    GenreService genreService;
    @MockBean
    AuditKafkaProducer producer;


    @Autowired
    BookService service;

    @Test
    void saveBook() {
        var uuid = UUID.randomUUID();
        var mockedInstant = Instant.parse("2023-02-11T12:34:56Z");
        try (var instantMock = Mockito.mockStatic(Instant.class);
             var uuidMock = Mockito.mockStatic(UUID.class)) {
            uuidMock.when(UUID::randomUUID).thenReturn(uuid);
            instantMock.when(Instant::now).thenReturn(mockedInstant);

            when(repository.save(any())).thenReturn(getBook());
            when(authorService.findByName(any())).thenReturn(getAuthor());
            when(genreService.findByName(any())).thenReturn(getGenre());
            when(repository.findById(any())).thenReturn(Optional.of(getBook()));

            var bookDto = getBookDto();
            bookDto.setId(null);

            var book = service.saveBook(bookDto, USER_ID);

            book.setId(null);
            verify(repository, times(1)).save(eq((book)));
            verify(producer, times(1)).publish(getAuditKafkaMessage(111L, BOOK_ID, SAVE_BOOK, uuid.toString()));
        }
    }

    @Test
    void updateBook() {
        var uuid = UUID.randomUUID();
        var mockedInstant = Instant.parse("2023-02-11T12:34:56Z");
        try (var instantMock = Mockito.mockStatic(Instant.class);
             var uuidMock = Mockito.mockStatic(UUID.class)) {
            uuidMock.when(UUID::randomUUID).thenReturn(uuid);
            instantMock.when(Instant::now).thenReturn(mockedInstant);

            when(repository.save(any())).thenReturn(getBook());
            when(authorService.findByName(any())).thenReturn(getAuthor());
            when(genreService.findByName(any())).thenReturn(getGenre());
            when(repository.findById(any())).thenReturn(Optional.of(getBook()));

            service.updateBook(getBookDto(), USER_ID);

            verify(repository, times(1)).save(eq(getBook()));
            verify(repository, times(1)).findById(1L);
            verify(producer, times(1)).publish(getAuditKafkaMessage(111L, BOOK_ID, UPDATE_BOOK, uuid.toString()));
        }
    }

    @Test
    void deleteBookById() {
        var uuid = UUID.randomUUID();
        var mockedInstant = Instant.parse("2023-02-11T12:34:56Z");
        try (var instantMock = Mockito.mockStatic(Instant.class);
             var uuidMock = Mockito.mockStatic(UUID.class)) {
            uuidMock.when(UUID::randomUUID).thenReturn(uuid);
            instantMock.when(Instant::now).thenReturn(mockedInstant);
            when(repository.findById(BOOK_ID)).thenReturn(Optional.of(getBook()));

            service.deleteBookById(BOOK_ID, USER_ID);

            verify(repository, times(1)).delete(getBook());
            verify(producer, times(1)).publish(getAuditKafkaMessage(111L, BOOK_ID, DELETE_BOOK, uuid.toString()));
        }
    }

    @Test
    void not_send_kafka_message_if_book_not_found() {
        when(repository.findById(BOOK_ID)).thenReturn(Optional.empty());

        service.deleteBookById(BOOK_ID, USER_ID);

        verify(repository, times(0)).delete(any());
        verify(producer, times(0)).publish(any());
    }

    @Test
    void getAllBook() {
        var uuid = UUID.randomUUID();
        var mockedInstant = Instant.parse("2023-02-11T12:34:56Z");
        try (var instantMock = Mockito.mockStatic(Instant.class);
             var uuidMock = Mockito.mockStatic(UUID.class)) {
            uuidMock.when(UUID::randomUUID).thenReturn(uuid);
            instantMock.when(Instant::now).thenReturn(mockedInstant);

            when(repository.findAll()).thenReturn(getBookList());

            var actual = service.findAllBooks(USER_ID);

            assertEquals(getBookList(), actual);
            verify(producer, times(1)).publish(getAuditKafkaMessage(111L, null, GET_LIST_BOOKS, uuid.toString()));
        }
    }

    @Test
    void findBookById() {
        var uuid = UUID.randomUUID();
        var mockedInstant = Instant.parse("2023-02-11T12:34:56Z");
        try (var instantMock = Mockito.mockStatic(Instant.class);
             var uuidMock = Mockito.mockStatic(UUID.class)) {
            uuidMock.when(UUID::randomUUID).thenReturn(uuid);
            instantMock.when(Instant::now).thenReturn(mockedInstant);
            when(repository.findById(eq(BOOK_ID))).thenReturn(Optional.of(getBook()));

            var actual = service.findBookById(BOOK_ID, USER_ID);

            assertEquals(getBook(), actual);
            verify(producer, times(1)).publish(getAuditKafkaMessage(111L, BOOK_ID, GET_BOOK, uuid.toString()));
        }
    }

    private List<Book> getBookList() {
        var book1 = getBook();
        book1.setId(2L);
        var book0 = getBook();
        List<Book> listBooks = new ArrayList<>();
        listBooks.add(book0);
        listBooks.add(book1);
        return listBooks;
    }

    private Book getBook() {
        return new Book(BOOK_ID, Book_NAME, getAuthor(), getGenre(), List.of(new Comment()));
    }


    private BookDto getBookDto() {
        return new BookDto(BOOK_ID, Book_NAME, AUTHOR_1, GENRE_2);
    }

    private Genre getGenre() {
        return new Genre(GENRE_ID, GENRE_NAME);
    }

    private Author getAuthor() {
        return new Author(AUTHOR_ID, AUTHOR_NAME);
    }

    private AuditKafkaMessage getAuditKafkaMessage(Long userId, Long bookId, AuditType eventType, String uuid) {
        return AuditKafkaMessage.builder()
            .eventId(uuid)
            .eventType(eventType)
            .timestamp(Instant.now().toEpochMilli())
            .message(AuditKafkaMessage.AuditEventMessage.builder()
                         .userId(userId)
                         .bookId(bookId)
                         .build()).build();
    }
}