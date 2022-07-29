package com.ff.furry_friend.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@AllArgsConstructor
@ToString
@Entity     //여러 엔티티간 연관관계를 정의
@Builder    //복합 객체의 생성 과정과 표현 방법을 분리하여 동일한 생성 절차에서 서로 다른 표현 결과를 만들 수 있게 하는 패턴
@Getter
@Setter
@NoArgsConstructor  //기본 생성자
public class product {
    @Id
    private String pro_name;
    @Column
    private Integer category;
    @Column
    private String pro_number;
    @Column
    private String pro_image;
    @Column
    private String pro_explain;
    @Column
    private Integer pro_price;
    @Column
    private String pro_option;
    @Column
    private Integer pro_view;


    public product toEntity(){
        return new product(pro_name, category, pro_number, pro_image, pro_explain, pro_price, pro_option, pro_view);
    }
}
