package com.ff.furry_friend.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity     //여러 엔티티간 연관관계를 정의
@Getter

@NoArgsConstructor  //기본 생성자
@AllArgsConstructor

//@Builder    //복합 객체의 생성 과정과 표현 방법을 분리하여 동일한 생성 절차에서 서로 다른 표현 결과를 만들 수 있게 하는 패턴
public class product {

    @Id
    @Setter
    @GeneratedValue
    private Long pro_id;

    @Setter
    @Column(nullable = false)
    private String pro_name;    //이름

    @Setter
    @Column(nullable = false)
    private Integer category;   //카테고리 번호

    @Column(nullable = false)
    private String pro_number;  //번호

    @Column(nullable = false)
    private String pro_image;   //사진

    @Column(nullable = false)
    private String pro_explain; //설명


    @Column(nullable = false)
    private Integer pro_price;  //가격


    @Column(nullable = false)
    private String pro_option;  // 사이즈나 옵션등

    @Column(nullable = false)
    private Integer pro_view;   //조회수

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<basket> baskets = new ArrayList<>();

    //?
//    @Builder.Default
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product", cascade = CascadeType.ALL)
//    private List<basket> basket = new ArrayList<>();


}
