package ru.practicum.mainservice.comment.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.mainservice.comment.dto.CommentResponseDto;
import ru.practicum.mainservice.comment.dto.NewCommentDto;
import ru.practicum.mainservice.comment.service.CommentService;

import javax.validation.constraints.Min;
import java.util.List;


@RestController
@Slf4j
@RequiredArgsConstructor
@Validated
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/comments/{userId}/{eventId}")
    public CommentResponseDto createComment(@PathVariable Long userId,
                                            @PathVariable Long eventId,
                                            @RequestBody NewCommentDto commentDto) {
        log.info("POST запрос на создание комментария пользователем с ID = {} к событию с ID = {}", userId, eventId);
        return commentService.createComment(userId, eventId, commentDto);
    }

    @GetMapping("/comments/{eventId}")
    public List<CommentResponseDto> getComments(@PathVariable Long eventId,
                                                @RequestParam(value = "from", defaultValue = "0") @Min(0) Integer from,
                                                @RequestParam(value = "size", defaultValue = "10") @Min(1) Integer size) {
        log.info("GET запрос на получение всех комментариев события с ID = {}", eventId);
        return commentService.getComments(eventId, from, size);
    }

    @PatchMapping("/comments/{userId}/{commentId}")
    public CommentResponseDto updateComment(@PathVariable Long userId,
                                            @PathVariable Long commentId,
                                            @RequestBody NewCommentDto commentDto) {
        log.info("PATCH запрос на редактирование комментария с ID = {} пользователем с ID = {}", commentId, userId);
        return commentService.updateComment(userId, commentId, commentDto);
    }

    @DeleteMapping("/public/comments/{userId}/{commentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void userDeleteComment(@PathVariable Long userId, @PathVariable Long commentId) {
        log.info("DELETE запрос на удаление комментария с ID = {} пользователем с ID = {}", commentId, userId);
        commentService.deleteComment(userId, commentId);
    }

}
