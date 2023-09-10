package ru.lazarev.springcourse.shell;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.lazarev.springcourse.domain.Book;
import ru.lazarev.springcourse.dto.BookDto;
import ru.lazarev.springcourse.service.BookService;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@ShellComponent
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookCommands {

    BookService bookService;

    ObjectMapper mapper;

    @ShellMethod(value = "get all books", key = {"--list", "-l"})
    public String getAllBooks() {
        return bookService.findAllBooks().stream()
            .map(BookDto::toString).collect(Collectors.joining(",", "[", "]"));
    }

    @ShellMethod(value = "get book by id", key = {"--get", "-g"})
    public String getBookById(@ShellOption Long id) {
        return bookService.findBookById(id).toString();
    }

    @ShellMethod(value = "get book by id", key = {"--delete", "-d"})
    public void deleteBookById(@ShellOption Long id) {
        bookService.deleteBookById(id);
    }

    @ShellMethod(value = "add book", key = {"--add", "-a"})
    public void addBook(@ShellOption String jsonBook) throws JsonProcessingException {
        var book = mapper.readValue(jsonBook, Book.class);
        book.setId(null);
        bookService.saveBook(book);
    }

    @ShellMethod(value = "add book", key = {"--update", "-u"})
    public void updateBook(@ShellOption String jsonBook) throws JsonProcessingException {
        var newBook = mapper.readValue(jsonBook, Book.class);
        bookService.updateBook(newBook);
    }
}