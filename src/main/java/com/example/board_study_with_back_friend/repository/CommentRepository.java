package com.example.board_study_with_back_friend.repository;

import com.example.board_study_with_back_friend.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

}
