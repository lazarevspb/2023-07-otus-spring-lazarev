package ru.lazarev.springcourse.library.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.lazarev.springcourse.library.dto.ContentDto;

@FeignClient(name = "storage")
public interface StorageServiceProxy {

    @GetMapping(value = "/content/{bookId}")
    public ContentDto getContent(@PathVariable Long bookId);
}
