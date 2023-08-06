package ru.lazarev.springcourse.reader.impl;

import org.springframework.stereotype.Component;
import ru.lazarev.springcourse.reader.Reader;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.MessageFormat;

@Component
public class CSVReaderImpl implements Reader {

    public static final String NO_RESOURCE_EXCEPTION_MESSAGE = "Failed to get resource with filename: [{0}]";

    @Override
    public String readTextResource(String filename) {
        try {
            var uri = getClass().getResource(MessageFormat.format("/{0}", filename)).toURI();
            return Files.readString(Paths.get(uri));
        } catch (Exception e) {
            throw new RuntimeException(MessageFormat.format(NO_RESOURCE_EXCEPTION_MESSAGE, filename));
        }
    }
}
