package com.ff.furry_friend.repository.service;

import com.ff.furry_friend.entity.user;
import com.ff.furry_friend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TestService {
    @Autowired
    private final UserRepository userRepository;

    public TestService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


}