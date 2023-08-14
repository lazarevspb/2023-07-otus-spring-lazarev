package ru.lazarev.springcourse.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Question {

    String question;

    List<String> answers;

    Integer numberCorrectAnswer;
}
