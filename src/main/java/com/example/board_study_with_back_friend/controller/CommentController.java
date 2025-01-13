package com.example.board_study_with_back_friend.controller;

import com.example.board_study_with_back_friend.controller.request.CommentRequest;
import com.example.board_study_with_back_friend.controller.response.CommentResponse;
import com.example.board_study_with_back_friend.domain.Comment;
import com.example.board_study_with_back_friend.dto.CommentDto;
import com.example.board_study_with_back_friend.repository.CommentRepository;
import com.example.board_study_with_back_friend.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    // 댓글 저장 > 게시글 찾아와서, 그곳 List<Comment>에 추가해주는 작업 필요
    @PostMapping("/comment")
    public ResponseEntity<CommentResponse> postComment(@RequestBody CommentRequest commentRequest) {
        return ResponseEntity.ok().body(new CommentResponse(commentService.save(commentRequest)));
    }

    @GetMapping("/comments")
    public ResponseEntity<List<CommentDto>> getComments() {
        return ResponseEntity.ok().body(commentService.findAll());
    }

    @DeleteMapping("/comment/{id}")
    public ResponseEntity<Comment> deleteComment(@PathVariable Long id) {
        commentService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
