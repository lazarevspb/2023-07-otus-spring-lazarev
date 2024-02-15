package ru.lazarev.springcourse.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import ru.lazarev.springcourse.entities.Role;
import ru.lazarev.springcourse.repositories.RoleRepository;
import ru.lazarev.springcourse.service.RoleService;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleServiceImpl implements RoleService {
    RoleRepository roleRepository;

    @Override public Role getUserRole() {
        return roleRepository.findByName("ROLE_USER").get();
    }
}
