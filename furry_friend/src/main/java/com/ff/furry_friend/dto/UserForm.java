package com.ff.furry_friend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.sql.Time;

@AllArgsConstructor
@ToString
@Getter
public class UserForm {

    private String id;
    private String pw;
    private String address;
    private String name;
    private String phone;
    private int save_money;
}
