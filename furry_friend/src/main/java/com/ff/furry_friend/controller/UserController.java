package com.ff.furry_friend.controller;

import com.ff.furry_friend.dto.UserForm;
import com.ff.furry_friend.entity.user;
import com.ff.furry_friend.repository.service.KakaoAPI;
import com.ff.furry_friend.repository.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

@Controller
public class UserController {
    @Autowired
    private final UserService userService;

    @Autowired
    private final KakaoAPI kakaoAPI;

    @Autowired
    public UserController(UserService userService, KakaoAPI kakaoAPI) {
        this.userService = userService;
        this.kakaoAPI = kakaoAPI;
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

    @GetMapping("/user/login")
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


    @GetMapping("/user/kakao")
    public String getCI(@RequestParam String code, Model model) throws IOException {
        System.out.println("code = " + code);
        String access_token = kakaoAPI.getToken(code);
        Map<String, Object> userInfo = kakaoAPI.getUserInfo(access_token);
        model.addAttribute("code", code);
        model.addAttribute("access_token", access_token);
        model.addAttribute("userInfo", userInfo);
        System.out.println(userInfo.get("id").toString() + userInfo.get("nickname").toString() + userInfo.get("email").toString());

        //ci는 비즈니스 전환후 검수신청 -> 허락받아야 수집 가능
        return "user/logininformation";
    }

//    @RequestMapping(value="/logout")
//    public String logout(HttpSession session) {
//        kakaoLoginService.kakaoLogout((String)session.getAttribute("access_Token"));
//        session.removeAttribute("access_Token");
//        session.removeAttribute("userId");
//        return "user/login";
//    }
}
