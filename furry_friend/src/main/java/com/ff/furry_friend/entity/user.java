package com.ff.furry_friend.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity     //여러 엔티티간 연관관계를 정의
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)

public class user {

    @Id
    @GeneratedValue
    private int create_id;
    @Column(nullable = false)
    private String id;
    @Column
    private String pw;
    @Column
    private String address;
    @Column(nullable = false)
    private String name;
    @Column
    private String phone;
    @Column(nullable = false)
    private int save_money;
    @Column(nullable = false)
    private String create_time;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<basket> users = new ArrayList<>();


}
