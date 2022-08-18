package com.ff.furry_friend.service;

import com.ff.furry_friend.entity.user;
import com.ff.furry_friend.repository.UserRepository;
import com.ff.furry_friend.repository.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    EntityManager em;

    @Test
    @Transactional
    void 회원가입() throws Exception {
        //Given
        user user = new user();
        user.setId("spring10");

        //When
        Long saveId = userService.create(user);
        em.persist(saveId); //추가 JPA가 관리 유저 추가

//        em.flush();
//        em.clear();

        //Then
        user finduser = userService.findOne(saveId).get();
        assertEquals(user.getName(), finduser.getName());
    }

    @Test
    void 중복조회() throws Exception {
        //Given
        user user = new user();
        user.setId("spring");

        user user1 = new user();
        user1.setId("spring");

        //When
        userService.create(user);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> userService.create(user1));//예외가 발생해야 한다.

        em.flush();
        em.clear();

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원 아이디입니다.");
    }

    @Test
    void 로그인() {

    }
}
