package com.ff.furry_friend.controller;

import com.ff.furry_friend.entity.product;
import com.ff.furry_friend.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {

    ProductService productService;

    @GetMapping("category")
    public String category(Model model){
        List<product> li = productService.findAll();


        model.addAttribute("li", li);
        return "category";
    }

    @GetMapping("category/pet")
    public String pet(){
        return "pet";
    }
}
