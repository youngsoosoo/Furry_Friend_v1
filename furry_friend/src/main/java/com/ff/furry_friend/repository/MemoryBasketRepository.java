package com.ff.furry_friend.repository;

import com.ff.furry_friend.entity.basket;
import com.ff.furry_friend.entity.product;

import javax.persistence.EntityManager;
import java.util.List;

public class MemoryBasketRepository implements BasketRepository{

    private final EntityManager em;

    public MemoryBasketRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public basket shopping(basket basket){
        em.persist(basket);
        return basket;
    }

    @Override
    public List<product> findByName(String name){
        List<product> result =  em.createQuery("select p from product p where p.pro_name = :pro_name", product.class)
                .setParameter("pro_name", name)
                .getResultList();
        return result;
    }
}
