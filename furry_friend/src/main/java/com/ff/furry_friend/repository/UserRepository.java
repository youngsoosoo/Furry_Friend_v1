package com.ff.furry_friend.repository;

import com.ff.furry_friend.entity.user;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository {

    user save(user user);

    Optional<user> findByCreate_Id(int id);

    Optional<user> findById(String id);

    List<user> findAll();

    int findid(String id);

    int findCreate_id(String id);

    int Create_id(int id);

    user findByUserid(String id);
}
