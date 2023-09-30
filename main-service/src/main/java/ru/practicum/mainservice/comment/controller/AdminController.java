package ru.practicum.mainservice.comment.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.mainservice.comment.service.CommentService;

@RestController
@Slf4j
@RequiredArgsConstructor
@Validated
public class AdminController {

    private final CommentService commentService;

    @DeleteMapping("/admin/comments/{commentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void adminDeleteComment(@PathVariable Long commentId) {
        log.info("DELETE запрос на удаление администратором пользовательского комментария с ID = {}", commentId);
        commentService.adminDeleteComment(commentId);
    }

}
