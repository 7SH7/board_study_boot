package com.example.board_study_with_back_friend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberDto {
    private Long id;

    private String userId;

    private String name;

    private String email;

    public static MemberDto from(Long id, String userId, String name, String email) {
        return MemberDto.builder()
            .id(id)
            .userId(userId)
            .name(name)
            .email(email)
            .build();
    }

}
