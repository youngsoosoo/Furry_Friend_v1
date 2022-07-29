package com.ff.furry_friend.repository;

import com.ff.furry_friend.entity.product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ProductRepository extends JpaRepository<product, Integer> {
    List<product> findAllByCategory(Integer category);
//    Optional<product> findByName(Integer category);
//
//    List<product> findAll();
}
