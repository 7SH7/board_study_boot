package com.example.board_study_with_back_friend.controller.response;

import com.example.board_study_with_back_friend.dto.CommentDto;
import lombok.Data;

@Data
public class CommentResponse {
    private String content;
    private Long articleId;

    public CommentResponse(String content, Long articleId){
        this.content = content;
        this.articleId = articleId;
    }

    public CommentResponse(CommentDto commentDto) {
        this.content = commentDto.getContent();
        this.articleId = commentDto.getArticleId();
    }
}
