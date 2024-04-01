package com.example.board_study_with_back_friend.service;

import com.example.board_study_with_back_friend.controller.request.ArticleRequest;
import com.example.board_study_with_back_friend.domain.Article;
import com.example.board_study_with_back_friend.dto.ArticleDto;
import com.example.board_study_with_back_friend.repository.ArticleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService {

	private final ArticleRepository articleRepository;

	public ArticleDto save(ArticleRequest articleRequest) {
		ArticleDto articleDto = ArticleDto.from(articleRepository.save(Article.from(articleRequest)));
		return articleDto;
	}

	public ArticleDto findById(Long id) {
//		must remind! Repository's type is Domain!
		Article article = articleRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다."));
		return ArticleDto.from(article);
	}

	public List<ArticleDto> findAll() {
		List<Article> articles = articleRepository.findAll();
		return articles.stream().map(ArticleDto::from).toList();
	}

	public void deleteById(Long id) {
		articleRepository.deleteById(id);
	}

	// @Transactional : If the value of object change, then the value of the DB change automatically.
	@Transactional
	public ArticleDto update(Long id, ArticleRequest articleRequest) {
		Article article = articleRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다."));
		article.update(ArticleDto.from(articleRequest));
		return ArticleDto.from(article);
	}

}
