package com.ff.furry_friend.controller;

import com.ff.furry_friend.dto.productDTO;
import com.ff.furry_friend.entity.product;
import com.ff.furry_friend.repository.ProductRepository;
import com.ff.furry_friend.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class MainController {

    ProductService productService;
    ProductRepository productRepository;

    @GetMapping("category")
    public String category(Model model, productDTO dto){
        List<product> li = productRepository.findAllByCategory(dto.toEntity().getCategory());
        System.out.println(li);
        model.addAttribute("li", li);
        return "category";
    }

    @GetMapping("category/pet")
    public String pet(){
        return "pet";
    }
}
