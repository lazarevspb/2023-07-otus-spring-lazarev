package ru.lazarev.springcourse.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import ru.lazarev.springcourse.dtos.RegistrationUserDto;
import ru.lazarev.springcourse.entities.User;

import java.util.Optional;

public interface UserService extends UserDetailsService {

    Optional<User> findByUsername(String username);

    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

    User createNewUser(RegistrationUserDto registrationUserDto);

    User getUserByToken(String token);
}
