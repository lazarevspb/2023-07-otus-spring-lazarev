package ru.lazarev.springcourse.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.lazarev.springcourse.dto.BookDto;
import ru.lazarev.springcourse.mapper.BookMapper;
import ru.lazarev.springcourse.service.AuthorService;
import ru.lazarev.springcourse.service.BookService;
import ru.lazarev.springcourse.service.GenreService;

import java.util.stream.Collectors;

@Controller
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/books")
public class BookController {
    BookService bookService;
    BookMapper bookMapper;
    AuthorService authorService;
    GenreService genreService;

    @GetMapping
    public String getAllBooks(Model model) {
        var books = bookService.findAllBooks().stream()
            .map(bookMapper::map).collect(Collectors.toList());
        model.addAttribute("books", books);
        return "index";
    }

    @GetMapping("/edit")
    public String editBooks(@RequestParam("id") long id, Model model) {
        model.addAttribute("authors", authorService.getAllAuthor());
        model.addAttribute("genres", genreService.getAllGenre());
        model.addAttribute("book", bookMapper.map(bookService.findBookById(id)));
        return "edit";
    }

    @PostMapping("/edit")
    public String updateBook(BookDto book) {
        bookService.updateBook(book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String updateBook(@PathVariable Long id) {
        bookService.deleteBookById(id);
        return "redirect:/books";
    }

    @GetMapping("/new")
    public String newBook(Model model) {
        model.addAttribute("authors", authorService.getAllAuthor());
        model.addAttribute("genres", genreService.getAllGenre());
        model.addAttribute("book", new BookDto());
        return "new-book-form";
    }

    @PostMapping("/save")
    public String saveNewBook(BookDto book) {
        bookService.saveBook(book);
        return "redirect:/books";
    }
}
