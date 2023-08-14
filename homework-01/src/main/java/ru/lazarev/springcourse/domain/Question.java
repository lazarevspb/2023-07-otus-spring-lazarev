package ru.lazarev.springcourse.domain;


import java.util.List;

public record Question(String question, List<String> answers) {
    public Question {
        answers = List.copyOf(answers);
    }

    public List<String> answers() {
        return List.copyOf(answers);
    }
}
