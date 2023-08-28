package ru.lazarev.springcourse.shell;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.ApplicationRunner;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;


@RequiredArgsConstructor
@ShellComponent
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TestCommands {

    ApplicationRunner applicationRunner;

    @ShellMethod(value = "start test", key = {"-s", "--start"})
    public void startTest() throws Exception {
        applicationRunner.run(null);
    }

    @ShellMethod(value = "exiting the test", key = {"-e", "--exit"})
    public void exit() {
        System.exit(0);
    }
}
