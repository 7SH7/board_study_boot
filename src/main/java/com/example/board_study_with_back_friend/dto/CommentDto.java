package com.example.board_study_with_back_friend.dto;

import com.example.board_study_with_back_friend.domain.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentDto {
    private Long id;
    private String content;
    private Long articleId;

    public static CommentDto from(Comment comment){
        return CommentDto.builder()
            .id(comment.getId())
            .content(comment.getContent())
            .articleId(comment.getArticle().getId())
            .build();
    }
}
