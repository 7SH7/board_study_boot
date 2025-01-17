package com.example.board_study_with_back_friend.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userId;

    private String name;

    private String email;

    private Member(String userId ,String name, String email) {
        this.userId = userId;
        this.name = name;
        this.email = email;
    }
    public static Member of(String userId, String name, String email) {
        return new Member(userId, name, email);
    }



}
