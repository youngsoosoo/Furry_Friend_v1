package com.ff.furry_friend.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@ToString
@Entity     //여러 엔티티간 연관관계를 정의
@Builder    //복합 객체의 생성 과정과 표현 방법을 분리하여 동일한 생성 절차에서 서로 다른 표현 결과를 만들 수 있게 하는 패턴
@Getter
@NoArgsConstructor  //기본 생성자
public class product {
    @Id
    @Setter
    private String pro_name;    //이름
    @Column
    @Setter
    private Integer category;   //카테고리 번호

    @Column
    private String pro_number;  //번호
    @Column
    private String pro_image;   //사진
    @Column
    private String pro_explain; //설명
    @Column
    private Integer pro_price;  //가격
    @Column
    private String pro_option;  // 사이즈나 옵션등
    @Column
    private Integer pro_view;   //조회수

    @Builder.Default
    @OneToMany(mappedBy = "product")
    private List<basket> basket = new ArrayList<>();
}
