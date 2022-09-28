package com.ff.furry_friend.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity     //여러 엔티티간 연관관계를 정의
@Getter
@Setter
@NoArgsConstructor  //기본 생성자
@AllArgsConstructor
public class comment {

    @Id
    @GeneratedValue
    private int commentid;

    private String content;

    private String img;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private user user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pro_id")
    private product product;
}
