package ru.lazarev.springcourse.service.impl;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import ru.lazarev.springcourse.service.IOService;

import java.io.PrintStream;
import java.util.Scanner;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class IOServiceStreamsImpl implements IOService {

    PrintStream output;

    Scanner input;

    public IOServiceStreamsImpl() {
        output = System.out;
        input = new Scanner(System.in);
    }

    @Override
    public void outputString(String s) {
        output.println(s);
    }

    @Override
    public int readIntWithPrompt(String prompt) {
        outputString(prompt);
        return Integer.parseInt(input.nextLine());
    }

    @Override
    public String readStringWithPrompt(String prompt) {
        outputString(prompt);
        return input.nextLine();
    }
}
