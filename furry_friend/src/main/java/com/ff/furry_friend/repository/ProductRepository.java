package com.ff.furry_friend.repository;

import com.ff.furry_friend.entity.product;

import java.util.List;
import java.util.Optional;


public interface ProductRepository {

    Optional<product> findByName(Integer category);

    List<product> findAll();
}
