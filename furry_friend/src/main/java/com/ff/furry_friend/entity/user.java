package com.ff.furry_friend.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity     //여러 엔티티간 연관관계를 정의
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)

public class user {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int create_id;
    private String id;
    @Setter
    private String pw;
    private String address;
    private String name;
    private String phone;
    private int save_money;

    @CreationTimestamp
    private Timestamp createtime;

    @Enumerated(EnumType.STRING)
    @Setter
    private Role role;

    private String provider;    // oauth2를 이용할 경우 어떤 플랫폼을 이용하는지
    private String providerId;  // oauth2를 이용할 경우 아이디값


    @Builder(builderClassName = "UserDetailRegister", builderMethodName = "userDetailRegister")
    public user(String id, String pw, String address, String name, String phone, int save_money, Role role) {
        this.id = id;
        this.pw = pw;
        this.address = address;
        this.name = name;
        this.phone = phone;
        this.save_money = save_money;
        this.role = role;
    }

    @Builder(builderClassName = "OAuth2Register", builderMethodName = "oauth2Register")
    public user(String id, String pw, String address, String name, String phone, int save_money, Role role, String provider, String providerId) {
        this.id = id;
        this.pw = pw;
        this.address = address;
        this.name = name;
        this.phone = phone;
        this.save_money = save_money;
        this.role = role;
        this.provider = provider;
        this.providerId = providerId;
    }

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<basket> users = new ArrayList<>();


}
