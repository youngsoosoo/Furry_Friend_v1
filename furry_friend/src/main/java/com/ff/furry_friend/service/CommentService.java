package com.ff.furry_friend.service;

import com.ff.furry_friend.entity.comment;
import com.ff.furry_friend.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public List<comment> findComment(Long pro_id){
        return commentRepository.findComment(pro_id);
    }

    public void save(comment comment){
        commentRepository.save(comment);
    }

    public void update(comment comment){
        commentRepository.update(comment);
    }

    public void delete(int commentid){
        commentRepository.delete(commentid);
    }
}
