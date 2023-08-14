package ru.lazarev.springcourse.repository.impl;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import ru.lazarev.springcourse.domain.User;
import ru.lazarev.springcourse.domain.UserAnswers;
import ru.lazarev.springcourse.repository.UserAnswerRepository;

import java.util.HashMap;
import java.util.Map;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserAnswerRepositoryImpl implements UserAnswerRepository {

    Map<User, UserAnswers> map = new HashMap<>();

    @Override
    public UserAnswers save(UserAnswers userAnswers) {
        map.put(userAnswers.getUser(), userAnswers);
        return userAnswers;
    }

    @Override
    public UserAnswers getByUser(User user) {
        return map.get(user);
    }
}
