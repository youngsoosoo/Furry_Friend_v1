package com.ff.furry_friend.dto;

import lombok.*;

import java.sql.Time;

@AllArgsConstructor
@ToString
@Getter
@Setter
@NoArgsConstructor
public class UserForm {

    private String id;
    private String pw;
    private String address;
    private String name;
    private String phone;
    private int save_money;
}
