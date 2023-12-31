package ru.practicum.mainservice.comment.service;


import ru.practicum.mainservice.comment.dto.CommentResponseDto;
import ru.practicum.mainservice.comment.dto.NewCommentDto;

import java.util.List;

public interface CommentService {
    CommentResponseDto createComment(Long userId, Long eventId, NewCommentDto newCommentDto);

    List<CommentResponseDto> getComments(Long eventId, Integer from, Integer size);

    CommentResponseDto updateComment(Long userId, Long commentId, NewCommentDto newCommentDto);

    void adminDeleteComment(Long commentId);

    void deleteComment(Long userId, Long commentId);
}