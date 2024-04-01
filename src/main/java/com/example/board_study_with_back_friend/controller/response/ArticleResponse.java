package com.example.board_study_with_back_friend.controller.response;

import com.example.board_study_with_back_friend.dto.ArticleDto;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ArticleResponse {
	private String title;
	private String content;

	public static ArticleResponse from(ArticleDto articleDto){
		return ArticleResponse.builder()
			.title(articleDto.getTitle())
			.content(articleDto.getContent())
			.build();
	}


}
