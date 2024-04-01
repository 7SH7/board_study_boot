package com.example.board_study_with_back_friend.dto;

import com.example.board_study_with_back_friend.controller.request.ArticleRequest;
import com.example.board_study_with_back_friend.domain.Article;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArticleDto {

	private Long id;
	private String title;
	private String content;

	// 생성자 역할을 하는 것임을 기억하기
	public static ArticleDto from(Article article){
		return ArticleDto.builder()
			.id(article.getId())
			.title(article.getTitle())
			.content(article.getContent())
			.build();
	}

	public static ArticleDto from(ArticleRequest articleRequest){
		return ArticleDto.builder()
			.title(articleRequest.getTitle())
			.content(articleRequest.getContent())
			.build();
	}

	
}
