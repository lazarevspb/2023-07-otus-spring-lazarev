package ru.lazarev.springcourse.shell;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.lazarev.springcourse.domain.Comment;
import ru.lazarev.springcourse.dto.CommentDto;
import ru.lazarev.springcourse.service.CommentService;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@ShellComponent
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CommentCommands {

    ObjectMapper mapper;
    CommentService commentService;

    @ShellMethod(value = "", key = {"--comments"})
    public String getAllCommentsByBookId(@ShellOption Long id) {
        return commentService.findAllCommentsByBookId(id).stream()
            .map(Comment::toString)
            .collect(Collectors.joining(",", "[", "]"));
    }

    @ShellMethod(value = "", key = {"--comment"})
    public String getAllCommentsById(@ShellOption Long id) {
        return commentService.findCommentById(id).toString();
    }

    @ShellMethod(value = "add book", key = {"--addComment"})
    public void addComment(@ShellOption String jsonBook) throws JsonProcessingException {
        var comment = mapper.readValue(jsonBook, CommentDto.class);
        commentService.saveComment(comment);
    }

    @ShellMethod(value = "add book", key = {"--updComment"})
    public void updateComment(@ShellOption String jsonBook) throws JsonProcessingException {
        var comment = mapper.readValue(jsonBook, CommentDto.class);
        commentService.updateComment(comment);
    }

    @ShellMethod(value = "add book", key = {"--delComment"})
    public void deleteComment(@ShellOption Long id) {
        commentService.deleteCommentById(id);
    }
}
