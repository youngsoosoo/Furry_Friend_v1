package com.ff.furry_friend.repository;

import com.ff.furry_friend.entity.comment;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

public class MemoryCommentRepository implements CommentRepository{
    private final EntityManager em;

    private static int sequence = 0;

    public MemoryCommentRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Optional<comment> findComment(String name){
        List<comment> result = em.createQuery("select b from comment b where b.product.pro_name = :name", comment.class)
                .setParameter("name", name)
                .getResultList();

        return result.stream().findAny();
    }

    @Override
    public void save(comment comment) {
        comment.setCommentid(sequence++);
        em.persist(comment);
    }

    @Override
    public void updatecomment(comment comment) {    //수정 필요!!!!
        em.createQuery("update comment b set b.content = :content, b.img = :img where b.product.pro_id = :pro_id")
                .setParameter("content", comment.getContent())
                .setParameter("img", comment.getImg())
                .setParameter("pro_id", comment.getProduct().getPro_id());
    }

    @Override
    public void deletecomment(int id, int pro_id) {
        Query query = em.createQuery("delete from comment b where b.commentid = :id and b.product.pro_id = :pro_id")
                .setParameter("id", id)
                .setParameter("pro_id", pro_id);
        query.executeUpdate();
    }
}
