package com.ff.furry_friend.controller;

import com.ff.furry_friend.dto.UserForm;
import com.ff.furry_friend.entity.user;
import com.ff.furry_friend.repository.service.KakaoLoginService;
import com.ff.furry_friend.repository.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

@Controller
public class UserController {

    private final UserService userService;
    private final KakaoLoginService kakaoLoginService;

    @Autowired
    public UserController(UserService userService, KakaoLoginService kakaoLoginService) {
        this.userService = userService;
        this.kakaoLoginService = kakaoLoginService;
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
        boolean login = userService.Login(form);

        if(!login) {
            return "/user/login";
        } else{
            System.out.println("로그인 성공");
            session.setAttribute("userid", form.getId());
            return "redirect:/all";
        }
    }

    @ResponseBody
    @GetMapping("/user/kakao")
    public void  kakaoCallback(@RequestParam String code, HttpSession session) {

        String access_Token = kakaoLoginService.getKakaoAccessToken(code);
        System.out.println("controller access_token : " + access_Token);

        HashMap<String, Object> userInfo = kakaoLoginService.getUserInfo(access_Token);
        System.out.println("login Controller : " + userInfo);

        //    클라이언트의 이메일이 존재할 때 세션에 해당 이메일과 토큰 등록
        if (userInfo.get("email") != null) {
            session.setAttribute("userId", userInfo.get("email"));
            session.setAttribute("access_Token", access_Token);
        }
    }

    @RequestMapping(value="/logout")
    public String logout(HttpSession session) {
        kakaoLoginService.kakaoLogout((String)session.getAttribute("access_Token"));
        session.removeAttribute("access_Token");
        session.removeAttribute("userId");
        return "index";
    }
}
