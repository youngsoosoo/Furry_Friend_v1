package com.ff.furry_friend.service;

import com.ff.furry_friend.dto.UserForm;
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
    public void 회원가입() throws Exception {
        //Given
        user user = new user();
        user.setId("spring");

        //When
        int saveId = userService.create(user);

        //Then
        if(userService.findOne(saveId).isPresent()){
            user finduser = userService.findOne(saveId).get();
            assertEquals(user.getName(), finduser.getName());
        }
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


        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원 아이디입니다.");
    }

    @Test
    void 로그인() {
        //Given
        UserForm form = new UserForm();
        form.setId("spring");
        form.setPw("1234");
        //When
        if(userService.Login(form)){
            //Then
            String id = userService.findById(form);
            String pw = userService.findByPw(form);
            assertEquals(id, form.getId());
            assertEquals(pw, form.getPw());
        }
    }
}
