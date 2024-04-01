package com.example.board_study_with_back_friend.domain;

import com.example.board_study_with_back_friend.controller.request.ArticleRequest;
import com.example.board_study_with_back_friend.dto.ArticleDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Article {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String title;

	private String content;

	public static Article from(ArticleRequest articleRequest){
		return Article.builder()
			.title(articleRequest.getTitle())
			.content(articleRequest.getContent())
			.build();
	}

	public void update(ArticleDto articleDto){
		this.title = articleDto.getTitle();
		this.content = articleDto.getContent();

	}

}
