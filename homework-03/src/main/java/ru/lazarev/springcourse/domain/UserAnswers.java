package ru.lazarev.springcourse.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserAnswers {

    User user;

    List<Question> questions;

    List<Integer> numbersUserAnswersList;
}
