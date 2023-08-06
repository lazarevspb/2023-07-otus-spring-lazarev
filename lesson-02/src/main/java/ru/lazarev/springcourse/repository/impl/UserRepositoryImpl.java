package ru.lazarev.springcourse.repository.impl;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;
import ru.lazarev.springcourse.domain.User;
import ru.lazarev.springcourse.repository.UserRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Repository
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserRepositoryImpl implements UserRepository {

    Map<UUID, User> userMap = new HashMap<>();

    @Override
    public User save(User user) {
        var uuid = UUID.randomUUID();
        user.setUuid(uuid);
        userMap.put(uuid, user);
        return user;
    }

    @Override
    public User getById(UUID uuid) {
        return userMap.get(uuid);
    }
}
