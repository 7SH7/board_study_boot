package com.example.board_study_with_back_friend;

import com.example.board_study_with_back_friend.controller.request.ArticleRequest;
import com.example.board_study_with_back_friend.domain.Article;
import com.example.board_study_with_back_friend.dto.ArticleDto;
import com.example.board_study_with_back_friend.repository.ArticleRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;    // get, post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;      // status, jsonPath
import static org.assertj.core.api.Assertions.assertThat;


import java.util.List;

@SpringBootTest // 테스트를 위한 Spring Boot 어플리케이션 컨텍스트 제공
@AutoConfigureMockMvc   // MockMvc 생성 및 자동 구성
public class BlogApiControllerTest {

	@Autowired
	protected MockMvc mockMvc;

	@Autowired
	protected ObjectMapper objectMapper;    // 직렬화(자바 객체 -> Json) , 역직렬화(Json -> 자바 객체)를 위한 코드

	@Autowired
	private WebApplicationContext context;

	@Autowired
	ArticleRepository articleRepository;

	@BeforeEach
	public void mockMvcSetup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
		articleRepository.deleteAll();
	 }

	@DisplayName("Article 등록 테스트")
	@Test   // Junit5의 Test 어노테이션
	public void addArticleTest() throws Exception {
		// given
		final String url = "/article";
		final String title = "test 제목";
		final String content = "test 내용";
		final ArticleRequest articleRequest = new ArticleRequest(title, content);

		// 객체 JSon으로 직렬화
		final String requestBody = objectMapper.writeValueAsString(articleRequest);

		// when
		ResultActions result = mockMvc.perform(post(url)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(requestBody));

		// then
		result.andExpect(status().isOk());

		List<Article> articles = articleRepository.findAll();

		assertThat(articles.size()).isEqualTo(1);
		assertThat(articles.get(0).getTitle()).isEqualTo(title);
		assertThat(articles.get(0).getContent()).isEqualTo(content);

	}

	@DisplayName("Article 조회 테스트_1")
	@Test
	public void findAllArticles() throws Exception{
		// given
		final String url = "/articles";
		final String title = "test 제목";
		final String content = "test 내용";

		ArticleRequest articleRequest = new ArticleRequest(title, content);


		articleRepository.save(new Article(ArticleDto.from(articleRequest)));

		// 여기는 json 형식을 받지 않기 때문에 직렬화/역직렬화 과정이 필요 없음.

		// when
		final ResultActions resultActions = mockMvc.perform(get(url)
				.accept(MediaType.APPLICATION_JSON));

		// then
		resultActions
			.andExpect(status().isOk())
			.andExpect(jsonPath("$[0].title").value("test 제목"))
			.andExpect(jsonPath("$[0].content").value("test 내용"));
	}

	@DisplayName("Article 조회 테스트_2")
	@Test
	public void findArticle() throws Exception{
		// given
		final String url = "/article/{id}";
		final String title = "test 제목";
		final String content = "test 내용";

		ArticleRequest articleRequest = new ArticleRequest(title, content);

		Article savedArticle = articleRepository.save(new Article(ArticleDto.from(articleRequest)));

		// when
		final ResultActions resultActions = mockMvc.perform(get(url, savedArticle.getId()));

		// then
		resultActions
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.title").value("test 제목"))
			.andExpect(jsonPath("$.content").value("test 내용"));
	}

}
