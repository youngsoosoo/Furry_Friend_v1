package com.ff.furry_friend.repository;

import com.ff.furry_friend.entity.product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ProductRepository {
    Optional<product> findByCategoty(Integer category);

    List<product> findAll();
}
