package com.ff.furry_friend.repository;

import com.ff.furry_friend.entity.comment;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommentRepository {
    //댓글
    Optional<comment> findComment(String name);

    //댓글 저장
    void save(comment comment);

    //댓글 수정
    void update(comment comment);
    //댓글 삭제
    void delete(comment comment);
}
