package com.ff.furry_friend.controller;

import com.ff.furry_friend.dto.UserForm;
import com.ff.furry_friend.entity.user;
import com.ff.furry_friend.repository.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/create")
    public String Create(){
        return "user/create";
    }

    @PostMapping(value = "/user/create")
    public String create(UserForm form) {
        user user = new user();
        user.setId(form.getId());
        user.setPw(form.getPw());
        user.setAddress(form.getAddress());
        user.setName(form.getName());
        user.setPhone(form.getPhone());

        userService.create(user);

        return "user/login";
    }

    @GetMapping("/user/loign")
    public String Login(){
        return "user/login";
    }

    @PostMapping(value = "/user/login")
    public String Login(UserForm form, HttpSession session) {
        int login = userService.Login(form);

        if(login == 0) {
            return "/user/login";
        } else{
            System.out.println("로그인 성공");
            session.setAttribute("userid", form.getId());
            return "redirect:/all";
        }
    }
}
