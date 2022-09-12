package com.ff.furry_friend.service;

import com.ff.furry_friend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestService {
    @Autowired
    private final UserRepository userRepository;

    public TestService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


}