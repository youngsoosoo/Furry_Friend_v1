package com.ff.furry_friend.controller;

import com.ff.furry_friend.dto.UserForm;
import com.ff.furry_friend.entity.user;
import com.ff.furry_friend.service.CertifiedService;
import com.ff.furry_friend.service.KakaoAPI;
import com.ff.furry_friend.service.TestService;
import com.ff.furry_friend.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class UserController {
    @Autowired
    private final UserService userService;

    @Autowired
    private final KakaoAPI kakaoAPI;

    @Autowired
    private final CertifiedService certifiedService;

    @Autowired
    private final TestService testService;

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
            session.setAttribute("id", form.getId());
            return "redirect:/all";
        }
    }


    @GetMapping("/user/kakao")
    public String getCI(@RequestParam String code, Model model, HttpSession session) throws IOException {
        System.out.println("code = " + code);
        String access_token = kakaoAPI.getToken(code);
        Map<String, Object> userInfo = kakaoAPI.getUserInfo(access_token);
        session.setAttribute("access_token", access_token);
        model.addAttribute("code", code);
        model.addAttribute("access_token", access_token);
        model.addAttribute("userInfo", userInfo);
        System.out.println(userInfo.get("id").toString() + userInfo.get("nickname") + userInfo.get("email").toString());

        user user = new user();
        user.setId(userInfo.get("id").toString());
        user.setName(userInfo.get("nickname").toString());

        int result = userService.create(user);
        if(result == 0){
            session.setAttribute("id", user.getId());
            System.out.println(user.getId());
        }else{
            //수정 필요
        }

        //ci는 비즈니스 전환후 검수신청 -> 허락받아야 수집 가능
        return "user/logininformation";
    }

    @RequestMapping(value="/logout")
    public String logout(HttpSession session) {
        System.out.println("3");
        String access_Token = (String)session.getAttribute("access_Token");

        if(access_Token != null && !"".equals(access_Token)){
            System.out.println("1");
            kakaoAPI.kakaoLogout(access_Token);
            session.removeAttribute("access_Token");
            //session.removeAttribute("id");
        }else{
            System.out.println("2");
            System.out.println("access_Token is null");
            //return "redirect:/";
        }
        //return "index";
        return "redirect:/user/login";
    }

    //ajax
    //아이디 중복 확인
    @PostMapping("/idCheck")
    @ResponseBody
    public int idCheck(@RequestParam("id") String id) {
        return userService.nameCheck(id);
    }

    //휴대폰 인증
    @RequestMapping(value = "/phoneCheck", method = RequestMethod.POST)
    @ResponseBody
    public String sendSMS(@RequestParam("phone") String userPhoneNumber) { // 휴대폰 문자보내기
        int randomNumber = (int)((Math.random()* (9999 - 1000 + 1)) + 1000);//난수 생성

        certifiedService.certifiedPhoneNumber(userPhoneNumber,randomNumber);

        return Integer.toString(randomNumber);
    }

    @GetMapping("/test")
    public String test(){
        return "testpage";
    }

}
