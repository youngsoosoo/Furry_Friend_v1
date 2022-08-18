package com.ff.furry_friend.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity     //여러 엔티티간 연관관계를 정의
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)

public class user {

    @Id
    @GeneratedValue
    private Long create_id;

    @Column(nullable = false)
    private String id;

//    @Column(nullable = false)
    private String pw;

    private String address;

    private String name;

    private String phone;

    @Column
    private int save_money;

//    @Column(nullable = false)
    private String create_time;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<basket> users = new ArrayList<>();


    //?
//    @Builder.Default
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
//    private List<basket> basket = new ArrayList<>();


}
