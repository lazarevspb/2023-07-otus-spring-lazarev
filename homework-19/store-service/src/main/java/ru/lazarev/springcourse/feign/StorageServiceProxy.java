package ru.lazarev.springcourse.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.lazarev.springcourse.dto.ContentDto;

@FeignClient(name = "library")
public interface StorageServiceProxy {

    @GetMapping(value = "/content/{bookId}")
    public ContentDto getContent(@PathVariable Long bookId);
}
