package com.ff.furry_friend.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column
    private String mobile;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Builder
    public User(String name, String email, String mobile, Role role){
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.role = role;
    }

    public User update(String name, String picture){
        this.name = name;
        this.mobile = mobile;

        return this;
    }

    public String getRoleKey(){
        return this.role.getKey();
    }
}
