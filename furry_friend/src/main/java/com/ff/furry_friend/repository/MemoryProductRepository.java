package com.ff.furry_friend.repository;

import com.ff.furry_friend.entity.product;

import javax.persistence.EntityManager;
import java.util.*;

public class MemoryProductRepository implements ProductRepository {
    private final EntityManager em;

    public MemoryProductRepository(EntityManager em) {
        this.em = em;
    }


    @Override
    public List<product> findByCategoty(Integer category) {
        List<product> result =  em.createQuery("select p from product p where p.category = :category", product.class)
                .setParameter("category", category)
                .getResultList();
        return result;
        //반복을 돌리면서 member.getName()이 파라미터로 넘어온 값이랑 같은지 확인하는 것 끝까지 돌려서 없으면 Optional에 감싸서 반환
    }

    @Override
    public List<product> findByName(String name){
        List<product> result =  em.createQuery("select p from product p where p.pro_name = :pro_name", product.class)
                .setParameter("pro_name", name)
                .getResultList();
        return result;
    }

    @Override
    public List<product> findAll() {
        return em.createQuery("select p from product p", product.class).getResultList();
    }
}
