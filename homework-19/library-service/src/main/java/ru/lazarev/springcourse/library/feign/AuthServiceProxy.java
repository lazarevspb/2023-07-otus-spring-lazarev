package ru.lazarev.springcourse.library.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.lazarev.springcourse.library.dto.TokenVerifyRequest;
import ru.lazarev.springcourse.library.dto.UserProfileDto;

@FeignClient(name = "auth")
public interface AuthServiceProxy {

    @PostMapping(value = "/auth/verify")
    public UserProfileDto getUserByToken(@RequestBody TokenVerifyRequest tokenVerifyRequest);

    @GetMapping(value = "/auth/user/{id}")
    public UserProfileDto getUserById(@PathVariable Long id);
}