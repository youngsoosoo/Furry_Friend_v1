package com.ff.furry_friend.repository;

import com.ff.furry_friend.entity.basket;
import com.ff.furry_friend.entity.product;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BasketRepository {

    basket shopping(basket basket);

    List<product> findByName(String name);
}
