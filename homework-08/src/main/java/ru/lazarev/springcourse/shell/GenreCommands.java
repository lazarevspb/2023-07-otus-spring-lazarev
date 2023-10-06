package ru.lazarev.springcourse.shell;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.lazarev.springcourse.dto.GenreDto;
import ru.lazarev.springcourse.service.GenreService;

import java.util.List;

@RequiredArgsConstructor
@ShellComponent
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class GenreCommands {

    GenreService genreService;

    @ShellMethod(value = "get All genres", key = {"--genres"})
    public void getAllGenre() {
        List<GenreDto> books = genreService.getAllGenre();
        System.out.println(books);
    }
}
