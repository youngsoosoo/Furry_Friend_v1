package com.ff.furry_friend.controller;

import com.ff.furry_friend.entity.basket;
import com.ff.furry_friend.entity.product;
import com.ff.furry_friend.entity.user;
import com.ff.furry_friend.service.BasketService;
import com.ff.furry_friend.service.ProductService;
import com.ff.furry_friend.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class BasketController {
    @Autowired
    private final ProductService productService;

    @Autowired
    private final BasketService basketService;

    @Autowired
    private final UserService userService;

    @PostMapping("basket")
    public String Shopping_basket(@RequestParam(value = "name") String name, HttpSession session){   //장바구니
        List<product> li = productService.findName(name);
        Optional<user> result = userService.findUsers((String) session.getAttribute("id"));
        basket ba = new basket();
        ba.setProduct(li.get(0));
        ba.setAmount(1);
        ba.setUser(result.get());
        basketService.shopping(ba);
        return "redirect:/basket/check";
    }

    @GetMapping("/basket/check")
    public String Check(HttpSession session, Model model){
        Optional<basket> result = basketService.findUserBasket((String) session.getAttribute("id"));
        model.addAttribute("li", result.get());
        return "basket/check";
    }

    @PostMapping("/basket/delete")
    public String Delete(@RequestParam(value = "name") String name, HttpSession session){
        basketService.DeleteBasket(name);
        return "redirect:/basket/check";
    }
}
