package ru.lazarev.springcourse.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.lazarev.springcourse.dtos.RegistrationUserDto;
import ru.lazarev.springcourse.entities.User;
import ru.lazarev.springcourse.exceptions.ServiceException;
import ru.lazarev.springcourse.repositories.UserRepository;
import ru.lazarev.springcourse.service.RoleService;
import ru.lazarev.springcourse.service.UserService;
import ru.lazarev.springcourse.utils.JwtTokenUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    UserRepository userRepository;
    RoleService roleService;
    JwtTokenUtils jwtTokenUtils;
    PasswordEncoder passwordEncoder;

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException(String.format("User '%s' not found", username)));
        return new org.springframework.security.core.userdetails.User(
            user.getUsername(),
            user.getPassword(),
            user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList())
        );
    }

    @Override public User createNewUser(RegistrationUserDto registrationUserDto) {
        var user = new User();
        user.setUsername(registrationUserDto.getUsername());
        user.setEmail(registrationUserDto.getEmail());
        user.setPassword(passwordEncoder.encode(registrationUserDto.getPassword()));
        user.setRoles(List.of(roleService.getUserRole()));
        return userRepository.save(user);
    }

    @Override public User getUserByToken(String token) {
        return userRepository.findByUsername(jwtTokenUtils.getUsername(token))
            .orElseThrow(() -> new ServiceException(HttpStatus.BAD_REQUEST.value(), "User is not found"));
    }
}
