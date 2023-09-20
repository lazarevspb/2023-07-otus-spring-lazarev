package ru.lazarev.springcourse.shell;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@RequiredArgsConstructor
@ShellComponent
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CommonCommands {

    @ShellMethod(value = "exiting the library", key = {"-e", "--exit"})
    public void exit() {
        System.exit(0);
    }
}
