package ru.lazarev.springcourse.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.lazarev.springcourse.domain.User;
import ru.lazarev.springcourse.repository.UserRepository;
import ru.lazarev.springcourse.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
