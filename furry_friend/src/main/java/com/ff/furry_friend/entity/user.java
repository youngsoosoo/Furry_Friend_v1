package com.ff.furry_friend.entity;

import com.ff.furry_friend.oauth2.Role;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity     //여러 엔티티간 연관관계를 정의
@Getter
@Setter
@ToString
@NoArgsConstructor
public class user {

    @Id
    @GeneratedValue
    private int create_id;
    private String id;
    private String pw;
    private String address;
    private String name;
    private String phone;
    private int save_money;

    @Column(nullable = false)
    private String create_time;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<basket> users = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<comment> user_comment = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private Role role;

    @Builder
    public user(String name, String id, Role role) {
        this.id = id;
        this.name = name;
        this.role = role;
    }
    public user update(String name){
        this.name = name;

        return this;
    }

    public String getRoleKey(){
        return this.role.getKey();
    }
}
