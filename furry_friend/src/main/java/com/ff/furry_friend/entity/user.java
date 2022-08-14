package com.ff.furry_friend.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@ToString
@Entity     //여러 엔티티간 연관관계를 정의
@Builder    //복합 객체의 생성 과정과 표현 방법을 분리하여 동일한 생성 절차에서 서로 다른 표현 결과를 만들 수 있게 하는 패턴
@Getter
@Setter
@NoArgsConstructor  //기본 생성자
public class user {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long create_id;

    @Column
    private String id;
    @Column
    private String pw;
    @Column
    private String address;
    @Column
    private String name;
    @Column
    private String phone;
    @Column
    private int save_money;
    @Column
    private String create_time;

    @Builder.Default
    @OneToMany(mappedBy = "user")
    private List<basket> basket = new ArrayList<>();
}
