package ru.lazarev.springcourse.shell;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.lazarev.springcourse.dto.AuthorDto;
import ru.lazarev.springcourse.service.AuthorService;

import java.util.List;

@RequiredArgsConstructor
@ShellComponent
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthorCommands {

    AuthorService authorService;

    @ShellMethod(value = "get all authors", key = {"--authors"})
    public void getAllAuthor() {
        List<AuthorDto> books = authorService.getAllAuthor();
        System.out.println(books);
    }
}
