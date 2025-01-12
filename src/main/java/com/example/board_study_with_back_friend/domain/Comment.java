package com.example.board_study_with_back_friend.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Comment {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @ManyToOne @JoinColumn(name = "article_id")
    private Article article;

//    member에 대한 건 스스로 만들어 보기! 숙제임!!
//    private Long memberId;

    public Comment(String content, Article article) {
        this.content = content;
        this.article = article;
    }

}
