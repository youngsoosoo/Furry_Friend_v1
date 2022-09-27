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
    public void update(comment comment) {// 댓글 아이디가 일치할 때 가능한 sql 문, 작성자가 같을 때 수정 삭제 버튼이 나오게 할 예정이라 아이디는 달라도 됌
        em.createQuery("update comment b set b.content = :content, b.img = :img where b.commentid = :commentid")
                .setParameter("content", comment.getContent())
                .setParameter("img", comment.getImg())
                .setParameter("commentid", comment.getCommentid());
    }

    @Override
    public void delete(comment comment) {
        Query query = em.createQuery("delete from comment b where b.commentid = :id")
                .setParameter("id", comment.getCommentid());
        query.executeUpdate();
    }
}
