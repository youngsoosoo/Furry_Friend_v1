package com.ff.furry_friend.service;

import com.ff.furry_friend.entity.user;
import com.ff.furry_friend.repository.MemoryUserRepository;
import com.ff.furry_friend.repository.service.UserService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@SpringBootTest
@Transactional
public class UserServiceTest1 {

    @Autowired
    UserService userService;

    @Autowired
    MemoryUserRepository memoryUserRepository;

    @Autowired
    EntityManager em;

    @Test
    public void userSave() {
        user user = new user();
        user.setId("SpringID");

        String userId = user.getId();

        Long saveUserId = userService.create(user);


        Assertions.assertThat(userId).isEqualTo(saveUserId);

    }

}
