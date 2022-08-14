package com.ff.furry_friend.repository;

import com.ff.furry_friend.entity.user;

import javax.persistence.EntityManager;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

public class MemoryUserRepository implements UserRepository{
    private final EntityManager em;

    public MemoryUserRepository(EntityManager em) {
        this.em = em;
    }
    private static long sequence = 0L;
    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    SimpleDateFormat sdf = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");


    public user save(user user) {
        user.setCreate_id(sequence++);
        user.setCreate_time(sdf.format(timestamp));
        em.persist(user);
        return user;
    }

    public Optional<user> findById(String id) {
        user user = em.find(user.class, id);
        return Optional.ofNullable(user);
    }

    public List<user> findAll() {
        return em.createQuery("select m from user m", user.class)
                .getResultList();
    }

    public Optional<user> findByName(String name) {
        List<user> result = em.createQuery("select m from user m where m.name = :name", user.class)
                .setParameter("name", name)
                .getResultList();
        return result.stream().findAny();
    }
}
