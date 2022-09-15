package com.ff.furry_friend.controller;

import com.ff.furry_friend.entity.user;
import com.ff.furry_friend.service.KakaoAPI;
import com.ff.furry_friend.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

@Controller
@AllArgsConstructor
public class SloginController {
    @Autowired
    private final UserService userService;

    @Autowired
    private final KakaoAPI kakaoAPI;

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

        userService.create(user);
        session.setAttribute("id", user.getId());
        System.out.println(user.getId());
        //카카오 회원이 있든 없든 세션 및 로그인 성공(없으면 자동 회원가입)
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

    //테스트중


}
