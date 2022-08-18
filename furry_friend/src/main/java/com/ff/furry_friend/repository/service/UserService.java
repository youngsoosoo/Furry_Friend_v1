package com.ff.furry_friend.repository.service;

import com.ff.furry_friend.dto.UserForm;
import com.ff.furry_friend.entity.user;
import com.ff.furry_friend.repository.MemoryUserRepository;
import com.ff.furry_friend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class UserService {

    @Autowired
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    /**
     * 회원가입
     */
    public Long create(user user) {
        validateDuplicateMember(user); //중복 회원 검증
        userRepository.save(user);
        return user.getCreate_id();
    }

    public int Login(UserForm form) {
        Optional<user> result = userRepository.findById(form.getId());
        if (!result.get().getId().equals(form.getId())) {
            return 0;
        }
        if (!result.get().getPw().equals(form.getPw())) {
            return 0;
        }
        return 1;
    }

    private void validateDuplicateMember(user user) {
        userRepository.findById(user.getId())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원 아이디입니다.");
                });
    }

    private void findId(user user) {
        userRepository.findById(user.getId())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     * 전체 회원 조회
     */
    public List<user> findUsers() {
        return userRepository.findAll();
    }

    public Optional<user> findOne(Long id) {
        return userRepository.findByCreate_Id(id);
    }
}
