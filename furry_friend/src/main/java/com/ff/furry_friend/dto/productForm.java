package com.ff.furry_friend.dto;

import lombok.AllArgsConstructor;
import lombok.ToString;


@AllArgsConstructor
@ToString
public class productForm {

    private String pro_name;
    private Integer category;
    private String pro_number;
    private String pro_image;
    private String pro_explain;
    private Integer pro_price;
    private String pro_option;
    private Integer pro_view;
}
