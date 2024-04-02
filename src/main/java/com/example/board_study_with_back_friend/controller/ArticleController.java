package com.example.board_study_with_back_friend.controller;

import com.example.board_study_with_back_friend.controller.request.ArticleRequest;
import com.example.board_study_with_back_friend.controller.response.ArticleResponse;
import com.example.board_study_with_back_friend.domain.Article;
import com.example.board_study_with_back_friend.dto.ArticleDto;
import com.example.board_study_with_back_friend.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ArticleController {

	private final ArticleService articleService;

	@PostMapping("/article")
	public ResponseEntity<ArticleResponse> addArticle(@RequestBody ArticleRequest articleRequest) {
		ArticleDto articleDto = articleService.save(articleRequest);
		return ResponseEntity.ok().body(ArticleResponse.from(articleDto));
	}

	@GetMapping("/article/{id}")
	public ResponseEntity<ArticleResponse> showArticle(@PathVariable Long id) {
		ArticleDto articleDto = articleService.findById(id);
		return ResponseEntity.ok().body(ArticleResponse.from(articleDto));
	}

	@GetMapping("/articles")
	public ResponseEntity<List<ArticleResponse>> showAllArticles(){
		List<ArticleResponse> articles = articleService.findAll().stream().map(ArticleResponse::from).toList();
		return ResponseEntity.ok().body(articles);
	}

	@DeleteMapping("/article/{id}")
	public ResponseEntity<ArticleResponse> deleteArticle(@PathVariable Long id) {
		articleService.deleteById(id);
		return ResponseEntity.ok().build();
	}

	@PatchMapping("/article/{id}")
	public ResponseEntity<ArticleResponse> updateArticle(@PathVariable Long id, @RequestBody ArticleRequest articleRequest) {
		ArticleDto articleDto = articleService.update(id, articleRequest);
		return ResponseEntity.ok().body(ArticleResponse.from(articleDto));
	}

}

