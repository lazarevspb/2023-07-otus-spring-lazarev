package ru.lazarev.springcourse.parser;

import ru.lazarev.springcourse.domain.Question;

import java.util.List;

public interface Parser {

   List<Question> parse(String csv);
}
