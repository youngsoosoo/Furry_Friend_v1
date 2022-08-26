package com.ff.furry_friend.dto;

import lombok.*;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class UserForm {

    private String id;
    private String pw;
    private String address;
    private String name;
    private String phone;
    private int save_money;
}
