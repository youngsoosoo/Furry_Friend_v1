package com.ff.furry_friend.repository;

import com.ff.furry_friend.entity.user;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository {

    user save(user user);

    Optional<user> findByCreate_Id(Long id);

    Optional<user> findById(String id);

    List<user> findAll();
}
