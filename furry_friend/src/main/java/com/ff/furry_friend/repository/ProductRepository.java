package com.ff.furry_friend.repository;

import com.ff.furry_friend.entity.product;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductRepository {
    List<product> findByCategoty(Integer category);
    List<product> findByName(String name);
    List<product> findAll();


}
