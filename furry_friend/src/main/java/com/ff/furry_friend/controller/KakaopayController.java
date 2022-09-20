package com.ff.furry_friend.controller;

import com.ff.furry_friend.dto.KakaoPay.KakaoPayApprovalVO;
import com.ff.furry_friend.entity.basket;
import com.ff.furry_friend.entity.product;
import com.ff.furry_friend.entity.user;
import com.ff.furry_friend.repository.BasketRepository;
import com.ff.furry_friend.repository.ProductRepository;
import com.ff.furry_friend.repository.UserRepository;
import com.ff.furry_friend.service.BasketService;
import com.ff.furry_friend.service.KakaopayService;
import groovy.util.logging.Slf4j;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Log                        //로그는 기록을 남기는 어노테이션
@Controller                 //컨트롤러 어노테이션
@RequiredArgsConstructor    //final이 붙거나 @NotNull 이 붙은 필드의 생성자를 자동 생성해주는 롬복 어노테이션
@Slf4j                      //로깅을 위한 어노테이션
public class KakaopayController {   //카카오 페이 결제 컨트롤러

    @Setter(onMethod_ = @Autowired) //자동 연결
    private KakaopayService kakaopay;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    BasketRepository basketRepository;
    @Autowired
    BasketService basketService;

    @GetMapping("/kakaoPay")    // kakaopay.mustache 뷰를 반환함.
    public String kakaoPayGet() {
        return "/kakaopay";
    }

    @PostMapping("/kakaoPay")   // POST로 방식으로 kakaoPayReady에 name 값과 userid 값을 넘겨준다.
    public String kakaoPay(@RequestParam(name = "name", required=false) String name/*값으로 넘겨준 name*/, HttpSession session) { //카카오페이 결제하기 버튼을 눌러 넘어오는 페이지
        List<product> pro = productRepository.findByName(name);
        //product set 해야함
        return "redirect:" + kakaopay.kakaoPayReady(pro, (String)session.getAttribute("id"));
    }

    @GetMapping("/paySuccess") //kakaopay 결제가 성공했을때 호출된다.
    public void kakaoPaySuccess(@RequestParam("pg_token") String pg_token, Model model, HttpSession session, @RequestParam(name = "name",  required=false) String name/*주소로 보내준 파라미터*/) {//성공시 보여주는 페이지
        log.info("kakaoPaySuccess get............................................");
        log.info("kakaoPaySuccess pg_token : " + pg_token);
        log.info(name);
        List<product> pro = productRepository.findByName(name);
        Optional<basket> ba = basketRepository.findByName(name);
        Optional<user> user = userRepository.findById((String)session.getAttribute("id"));
        /*장바구니에 담겨있던 상품이라면 제거와 결제 아니라면 그냥 결제, 파라미터로 이름과 id를 받아야함*/
        if(ba.isPresent() && ba.get().getProduct().getPro_name().equals(name) && ba.get().getUser().getId().equals(session.getAttribute("id"))){
            KakaoPayApprovalVO li = kakaopay.kakaoPayInfo(pg_token, pro, (String)session.getAttribute("id"));
            basketService.DeleteBasket(ba.get().getProduct().getPro_id(), user.get().getCreate_id());
            model.addAttribute("info", li);
        }
        else{
            KakaoPayApprovalVO li = kakaopay.kakaoPayInfo(pg_token, pro, (String)session.getAttribute("id"));
            model.addAttribute("info", li);
        }
    }
}