package com.ff.furry_friend.auth;

import com.ff.furry_friend.dto.Role;
import com.ff.furry_friend.dto.User;
import com.ff.furry_friend.entity.user;
import com.ff.furry_friend.service.KakaoAPI;
import com.ff.furry_friend.service.UserService;
import lombok.AllArgsConstructor;
import org.apache.maven.artifact.repository.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @Autowired private UserRepository userRepository;
    @Autowired private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/loginForm")
    public String loginForm(){
        return "login";
    }

    @GetMapping("/joinForm")
    public String joinForm(){
        return "join";
    }

    @PostMapping("/join")
    public String join(@ModelAttribute User user){
        user.setRole(Role.ROLE_USER);

        String encodePwd = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodePwd);

        userRepository.save(user);  //반드시 패스워드 암호화해야함
        return "redirect:/loginForm";
    }

    @GetMapping("/user")
    @ResponseBody
    public String user(){
        return "user";
    }

    @GetMapping("/manager")
    @ResponseBody
    public String manager(){
        return "manager";
    }

    @GetMapping("/admin")
    @ResponseBody
    public String admin(){
        return "admin";
    }



    // !!!! OAuth로 로그인 시 이 방식대로 하면 CastException 발생함
    @GetMapping("/form/loginInfo")
    @ResponseBody
    public String formLoginInfo(Authentication authentication, @AuthenticationPrincipal PrincipalDetails principalDetails){

        PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
        User user = principal.getUser();
        System.out.println(user);
        //User(id=2, username=11, password=$2a$10$m/1Alpm180jjsBpYReeml.AzvGlx/Djg4Z9/JDZYz8TJF1qUKd1fW, email=11@11, role=ROLE_USER, createTime=2022-01-30 19:07:43.213, provider=null, providerId=null)

        User user1 = principalDetails.getUser();
        System.out.println(user1);
        //User(id=2, username=11, password=$2a$10$m/1Alpm180jjsBpYReeml.AzvGlx/Djg4Z9/JDZYz8TJF1qUKd1fW, email=11@11, role=ROLE_USER, createTime=2022-01-30 19:07:43.213, provider=null, providerId=null)
        //user == user1

        return user.toString();
    }


    @GetMapping("/oauth/loginInfo")
    @ResponseBody
    public String oauthLoginInfo(Authentication authentication, @AuthenticationPrincipal OAuth2User oAuth2UserPrincipal){
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        Map<String, Object> attributes = oAuth2User.getAttributes();
        System.out.println(attributes);
        // PrincipalOauth2UserService의 getAttributes내용과 같음

        Map<String, Object> attributes1 = oAuth2UserPrincipal.getAttributes();
        // attributes == attributes1

        return attributes.toString();     //세션에 담긴 user가져올 수 있음음
    }


    @GetMapping("/loginInfo")
    @ResponseBody
    public String loginInfo(Authentication authentication, @AuthenticationPrincipal PrincipalDetails principalDetails){
        String result = "";

        PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
        if(principal.getUser().getProvider() == null) {
            result = result + "Form 로그인 : " + principal;
        }else{
            result = result + "OAuth2 로그인 : " + principal;
        }
        return result;
    }

}
