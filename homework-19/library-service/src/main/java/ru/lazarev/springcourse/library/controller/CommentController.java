package ru.lazarev.springcourse.library.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.lazarev.springcourse.library.dto.CommentDto;
import ru.lazarev.springcourse.library.model.UserProfile;
import ru.lazarev.springcourse.library.service.CommentService;

import java.util.List;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Slf4j
@RequestMapping("api/v1/comments")
public class CommentController {
    CommentService commentService;

    @GetMapping("/{id}")
    @Secured({"ROLE_USER", "ROLE_GUEST", "ROLE_ADMIN"})
    public ResponseEntity<List<CommentDto>> getAllCommentsByBookId(UserProfile userProfile, @PathVariable Long id) {
        return ResponseEntity.ok(commentService.findCommentsByBookId(id));
    }
}
