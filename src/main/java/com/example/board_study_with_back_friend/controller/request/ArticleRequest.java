package com.example.board_study_with_back_friend.controller.request;

import com.example.board_study_with_back_friend.domain.Article;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class ArticleRequest {
  private String title;
  private String content;



}
