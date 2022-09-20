package com.ff.furry_friend.controller;

import com.ff.furry_friend.entity.product;
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

import java.util.List;

@Controller
@AllArgsConstructor
public class MainController {
    @Autowired
    private final ProductService productService;

    @Autowired
    private final BasketService basketService;

    @Autowired
    private final UserService userService;


    @GetMapping("all")  //메인페이지로 만들어야함!!!
    public String category(Model model){
        List<product> li = productService.findAll();
        model.addAttribute("li", li);

        return "category";
    }

    @PostMapping("search")
    public String Search(@RequestParam(value = "search", required = false) String search, Model model){
        List<product> li = productService.findPartName(search);
        model.addAttribute("li", li);
        System.out.println(search);
        return "category";
    }

    @GetMapping("category")
    public String category(@RequestParam(value = "category", required = false) int category, Model model){
        if(category < 10){//카테고리 값이 없다면 실행 x 수정 필요
            List<product> li = productService.findAll();
            model.addAttribute("li", li);
        }else{
            List<product> li = productService.findCategory(category);
            model.addAttribute("li", li);
        }

        return "category";
    }

    @GetMapping("category/detail")
    public String pet(@RequestParam(value = "name") String name, Model model){
        List<product> li = productService.findName(name);
        model.addAttribute("li", li);
        return "detail";
    }

    @GetMapping("purchase")
    public String Purchase(@RequestParam(value = "name") String name){  //구매
        System.out.println(name);
        return null;
    }
}
