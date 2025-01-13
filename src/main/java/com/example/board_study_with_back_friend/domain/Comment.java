package com.example.board_study_with_back_friend.domain;

import com.example.board_study_with_back_friend.controller.request.CommentRequest;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class Comment {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @ManyToOne
    @JoinColumn(name = "article_id", nullable = false) // 외래키 지정
    private Article article;


//    member에 대한 건 스스로 만들어 보기! 숙제임!!
//    private Member member;

    public Comment(String content, Article article) {
        this.content = content;
        this.article = article;
    }

}
