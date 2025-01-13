package com.example.board_study_with_back_friend.controller.request;

import com.example.board_study_with_back_friend.domain.Comment;
import lombok.Data;

@Data
public class CommentRequest {
    private String content;
    private Long articleId;

    public CommentRequest(String content, Long articleId){
        this.content = content;
        this.articleId = articleId;
    }

}
