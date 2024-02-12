package ru.lazarev.springcourse.library.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.lazarev.springcourse.library.dto.BookDto;
import ru.lazarev.springcourse.library.mapper.BookMapper;
import ru.lazarev.springcourse.library.model.UserProfile;
import ru.lazarev.springcourse.library.service.BookService;
import ru.lazarev.springcourse.library.service.ContentService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Slf4j
@RequestMapping("api/v1/books")
public class BookController {
    BookService bookService;
    BookMapper bookMapper;
    ContentService contentService;

    @GetMapping("/content/{id}")
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public ResponseEntity<?> getContentByBookId(UserProfile userProfile, @PathVariable Long id) {
        return ResponseEntity.ok(contentService.getContent(id, userProfile.getUserId()));
    }

    @GetMapping
    @Secured({"ROLE_USER", "ROLE_GUEST", "ROLE_ADMIN"})
    public ResponseEntity<List<BookDto>> getAllBooks(UserProfile userProfile,
                                                     @RequestHeader(value = "Authorization", required = false) String authorization) {
        var books = bookService.findAllBooks(userProfile.getUserId()).stream()
            .map(bookMapper::map).collect(Collectors.toList());
        return ResponseEntity.ok(books);
    }

    @GetMapping("{id}")
    @Secured({"ROLE_USER", "ROLE_GUEST", "ROLE_ADMIN"})
    public ResponseEntity<BookDto> getBookById(UserProfile userProfile, @PathVariable Long id) {
        return ResponseEntity.ok(bookMapper.map(bookService.findBookById(id, userProfile.getUserId())));
    }

    @DeleteMapping("/{id}")
    @Secured({"ROLE_ADMIN"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteBook(UserProfile userProfile, @PathVariable Long id) {
        bookService.deleteBookById(id, userProfile.getUserId());
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<BookDto> updateBook(UserProfile userProfile, @RequestBody BookDto book) {
        bookService.updateBook(book, userProfile.getUserId());
        return ResponseEntity
            .ok(bookMapper.map(bookService.findBookById(book.getId(), userProfile.getUserId())));
    }

    @PostMapping
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<BookDto> saveBook(UserProfile userProfile, @RequestBody BookDto book) {
        return ResponseEntity.ok(bookMapper.map(bookService.saveBook(book, userProfile.getUserId())));
    }
}
