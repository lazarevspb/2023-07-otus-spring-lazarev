package ru.lazarev.springcourse.library.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import ru.lazarev.springcourse.library.domain.Author;
import ru.lazarev.springcourse.library.domain.Book;
import ru.lazarev.springcourse.library.domain.Comment;
import ru.lazarev.springcourse.library.domain.Genre;
import ru.lazarev.springcourse.library.dto.BookDto;
import ru.lazarev.springcourse.library.enums.AuditType;
import ru.lazarev.springcourse.library.kafka.AuditKafkaProducer;
import ru.lazarev.springcourse.library.kafka.message.AuditKafkaMessage;
import ru.lazarev.springcourse.library.repository.BookRepository;
import ru.lazarev.springcourse.library.service.AuthorService;
import ru.lazarev.springcourse.library.service.BookService;
import ru.lazarev.springcourse.library.service.GenreService;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static ru.lazarev.springcourse.library.enums.AuditType.DELETE_BOOK;
import static ru.lazarev.springcourse.library.enums.AuditType.GET_BOOK;
import static ru.lazarev.springcourse.library.enums.AuditType.GET_LIST_BOOKS;
import static ru.lazarev.springcourse.library.enums.AuditType.SAVE_BOOK;
import static ru.lazarev.springcourse.library.enums.AuditType.UPDATE_BOOK;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookServiceImpl implements BookService {

    BookRepository repository;

    AuthorService authorService;

    GenreService genreService;

    AuditKafkaProducer auditKafkaProducer;


    @Override
    @Transactional
    public List<Book> findAllBooks(Long userId) {
        var bookList = repository.findAll();
        bookList.sort(Comparator.comparing(Book::getId));
        auditKafkaProducer.publish(getAuditKafkaMessage(userId, null, GET_LIST_BOOKS));
        return bookList;
    }

    @Override
    public List<Comment> findAllCommentByBookId(Long bookId, Long userId) {
        return repository.findById(bookId).get().getComments().stream()
            .collect(Collectors.toList());
    }

    @Override
    public Book findBookById(Long bookId, Long userId) {
        auditKafkaProducer.publish(getAuditKafkaMessage(userId, bookId, GET_BOOK));
        return repository.findById(bookId).get();
    }

    @Override
    public Book saveBook(BookDto newBook, Long userId) {
        Book savedBook = null;
        if (Objects.nonNull(newBook.getId())) {
            var oldBook = repository.findById(newBook.getId());
            savedBook = repository.save(new Book(newBook.getId(), newBook.getTitle(), getAuthor(newBook),
                                                 getGenre(newBook),
                                                 getOldComments(oldBook)));
        } else {
            savedBook = repository.save(new Book(null, newBook.getTitle(), getAuthor(newBook),
                                                 getGenre(newBook),
                                                 null));

        }
        auditKafkaProducer.publish(getAuditKafkaMessage(userId, savedBook.getId(), SAVE_BOOK));
        return savedBook;
    }

    @Override
    public void updateBook(BookDto newBook, Long userId) {
        var book = saveBook(newBook, userId);
        auditKafkaProducer.publish(getAuditKafkaMessage(userId, book.getId(), UPDATE_BOOK));
    }

    private Author getAuthor(BookDto newBook) {
        return authorService.findByName(newBook.getAuthor());
    }

    private Genre getGenre(BookDto newBook) {
        return genreService.findByName(newBook.getGenre());
    }

    private List<Comment> getOldComments(Optional<Book> oldBook) {
        return oldBook.map(Book::getComments).orElse(null);
    }

    @Override
    public void deleteBookById(Long bookId, Long userId) {
        repository.findById(bookId)
            .ifPresent(entity -> {
                repository.delete(entity);
                auditKafkaProducer.publish(getAuditKafkaMessage(userId, bookId, DELETE_BOOK));
            });
    }


    private AuditKafkaMessage getAuditKafkaMessage(Long userId, Long bookId, AuditType eventType) {
        return AuditKafkaMessage.builder()
            .eventId(UUID.randomUUID().toString())
            .eventType(eventType)
            .timestamp(Instant.now().toEpochMilli())
            .message(AuditKafkaMessage.AuditEventMessage.builder()
                         .userId(userId)
                         .bookId(bookId)
                         .build()).build();
    }
}
