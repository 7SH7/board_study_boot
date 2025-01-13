package com.example.board_study_with_back_friend.domain;

import com.example.board_study_with_back_friend.controller.request.ArticleRequest;
import com.example.board_study_with_back_friend.dto.ArticleDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Article {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "article_id")
	private Long id;

	private String title;

	private String content;

	@OneToMany(
			mappedBy = "article",
			fetch = FetchType.LAZY,
			cascade = CascadeType.ALL,
			orphanRemoval = true)
	private List<Comment> comments =  new ArrayList<>();

	public static Article from(ArticleRequest articleRequest){
		return Article.builder()
			.title(articleRequest.getTitle())
			.content(articleRequest.getContent())
			.comments( new ArrayList<>())
			.build();
	}

	public Article(ArticleDto articleDto){
		this.title = articleDto.getTitle();
		this.content = articleDto.getContent();
		this.comments =  new ArrayList<>();
	}

	public void update(ArticleDto articleDto){
		this.title = articleDto.getTitle();
		this.content = articleDto.getContent();
	}

 }
