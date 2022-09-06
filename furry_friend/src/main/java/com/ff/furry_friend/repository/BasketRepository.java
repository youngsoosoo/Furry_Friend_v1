package com.ff.furry_friend.repository;

import com.ff.furry_friend.entity.basket;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BasketRepository {

    basket shopping(basket basket);

    Optional<basket> findByName(String name);

    Optional<basket> findUserBasket(String id);

    void DeleteBasket(Long pro_id, int id);
}
