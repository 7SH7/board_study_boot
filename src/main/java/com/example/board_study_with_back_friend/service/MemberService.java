package com.example.board_study_with_back_friend.service;

import com.example.board_study_with_back_friend.domain.Member;
import com.example.board_study_with_back_friend.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member saveOrUpdateUser(String userId, String email, String name) {
        // userId를 기준으로 사용자 검색
        Optional<Member> existingUser = memberRepository.findByUserId(userId);

        if (existingUser.isPresent()) {
            // 기존 사용자 정보 업데이트
            Member member = existingUser.get();
            member.setEmail(email);
            member.setName(name);
            return memberRepository.save(member);
        } else {
            // 새로운 사용자 저장
            Member newUser = new Member();
            newUser.setUserId(userId);
            newUser.setEmail(email);
            newUser.setName(name);
            return memberRepository.save(newUser);
        }
    }
}
