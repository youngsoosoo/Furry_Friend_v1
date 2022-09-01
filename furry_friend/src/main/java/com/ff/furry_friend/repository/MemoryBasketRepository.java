package com.ff.furry_friend.repository;

import com.ff.furry_friend.entity.basket;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class MemoryBasketRepository implements BasketRepository{

    private final EntityManager em;
    private static int sequence = 0;

    public MemoryBasketRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public basket shopping(basket basket){
        basket.setBasket_id(sequence++);
        em.persist(basket);
        return basket;
    }

    @Override
    public Optional<basket> findByName(String name){
        List<basket> result = em.createQuery("select b from basket b where b.product.pro_name = :name", basket.class)
                .setParameter("name", name)
                .getResultList();
        return result.stream().findAny();
    }
}
