package com.ff.furry_friend.repository;

import com.ff.furry_friend.entity.basket;
import com.ff.furry_friend.entity.product;

import javax.persistence.EntityManager;
import javax.persistence.Query;
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

    @Override
    public Optional<basket> findUserBasket(String id){
        List<basket> result = em.createQuery("select b from basket b where b.user.id = :id", basket.class)
                .setParameter("id", id)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public void DeleteBasket(Long pro_id, int id){
        product pro = new product();
        pro.setPro_id(pro_id);
        basket ba = new basket();
        ba.setProduct(pro);
        //수정 필요
        Query query = em.createQuery("delete from basket b where b.user.create_id = :id and b.product.pro_id = :pro_id")
                .setParameter("id", id)
                .setParameter("pro_id", pro_id);
        query.executeUpdate();
    }
}
