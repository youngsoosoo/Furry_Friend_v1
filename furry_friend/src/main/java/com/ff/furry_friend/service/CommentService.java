package com.ff.furry_friend.service;

import com.ff.furry_friend.entity.comment;
import com.ff.furry_friend.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Optional<comment> findComment(String name){
        return commentRepository.findComment(name);
    }

    public void save(comment comment){
        commentRepository.save(comment);
    }

    public void update(comment comment){
        commentRepository.update(comment);
    }

    public void delete(comment comment){
        commentRepository.delete(comment);
    }
}
