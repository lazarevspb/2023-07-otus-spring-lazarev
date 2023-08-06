package ru.lazarev.springcourse.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserAnswers {

    User user;

    List<Question> questions;

    List<Integer> numbersUserAnswersList;
}
