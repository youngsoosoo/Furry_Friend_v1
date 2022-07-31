package com.ff.furry_friend.controller;

import com.ff.furry_friend.dto.productDTO;
import com.ff.furry_friend.entity.product;
import com.ff.furry_friend.repository.ProductRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.EntityManager;
import java.util.List;

@Controller
public class MainController {


    ProductRepository productRepository;



    @GetMapping("category")
    public String category(Model model, productDTO dto){
        List<product> li = productRepository.findAll();
        System.out.println(li);
        model.addAttribute("li", li);
        return "category";
    }

    @GetMapping("category/pet")
    public String pet(){
        return "pet";
    }
}
