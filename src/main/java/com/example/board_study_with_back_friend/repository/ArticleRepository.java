package com.example.board_study_with_back_friend.repository;

import com.example.board_study_with_back_friend.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

}
