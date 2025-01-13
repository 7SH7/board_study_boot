package com.example.board_study_with_back_friend.service;

import com.example.board_study_with_back_friend.controller.request.CommentRequest;
import com.example.board_study_with_back_friend.domain.Article;
import com.example.board_study_with_back_friend.domain.Comment;
import com.example.board_study_with_back_friend.dto.CommentDto;
import com.example.board_study_with_back_friend.repository.ArticleRepository;
import com.example.board_study_with_back_friend.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final ArticleRepository articleRepository;

    public CommentDto save(CommentRequest commentRequest) {
        // 프론트가 보내준 게 진짜 있는지 확인하고자, 게시글 id로 게시글을 찾아온 것임.
        Article article = articleRepository.findById(commentRequest.getArticleId())
                .orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다."));

        Comment comment = new Comment(commentRequest.getContent(), article);
        return CommentDto.from(commentRepository.save(comment));
    }


    public List<CommentDto> findAll() {
        List<Comment>comments = commentRepository.findAll();
        return comments.stream().map(CommentDto::from).toList();
    }

    public void deleteById(Long id) {
        commentRepository.deleteById(id);
    }

}