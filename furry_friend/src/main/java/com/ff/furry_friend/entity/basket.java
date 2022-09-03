package com.ff.furry_friend.entity;

import lombok.*;

import javax.persistence.*;

@Entity     //여러 엔티티간 연관관계를 정의
@Getter
@Setter

@NoArgsConstructor
@AllArgsConstructor
@Builder    //복합 객체의 생성 과정과 표현 방법을 분리하여 동일한 생성 절차에서 서로 다른 표현 결과를 만들 수 있게 하는 패턴
public class basket {

    @Id
    @GeneratedValue
    private int basket_id;

    @Column(nullable = false)
    private Integer amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pro_id")
    private product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private user user;
}
