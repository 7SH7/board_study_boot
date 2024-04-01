package com.example.board_study_with_back_friend;

import com.example.board_study_with_back_friend.repository.ArticleRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

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
}
