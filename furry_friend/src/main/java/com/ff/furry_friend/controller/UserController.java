package com.ff.furry_friend.controller;

import com.ff.furry_friend.dto.UserForm;
import com.ff.furry_friend.entity.user;
import com.ff.furry_friend.service.CertifiedService;
import com.ff.furry_friend.service.TestService;
import com.ff.furry_friend.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@AllArgsConstructor
public class UserController {
    @Autowired
    private final UserService userService;

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


    //아이디 찾기
    @GetMapping("/user/findid")
    public String findidGet(){
        return "/user/findid";
    }
    @PostMapping("/user/findid")
    public String findid(@RequestParam("phone") String phone, @RequestParam("name") String name, Model model){
        model.addAttribute("id", userService.findPhone(phone, name));
        return "/user/login";
    }

    //비밀번호 찾기
    @GetMapping("/user/findpw")
    public String findpwGet(){
        return "/user/findpw";
    }
    @PostMapping("/user/findpw")
    public String findpw(@RequestParam("id") String id, Model model){
        model.addAttribute("pw", userService.findPw(id));
        return "/user/login";
    }
}
