package com.ff.furry_friend.service;

import com.ff.furry_friend.entity.product;
import com.ff.furry_friend.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductServiceTest {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProductService productService;


    @Test
    void findCategory() {
        //given
        product pro = new product();
        pro.category(24);
        //when
        List<product> category = productService.findCategory(pro.getCategory());
        //Then
        product find = productRepository.findByCategoty(category.get(0).getCategory()).get(0);
        assertEquals(pro.getCategory(), find.getCategory());
    }

    @Test
    void findAll() throws Exception{
        //given
        product pro = new product();
        pro.name("고양이 장난감");
        //when
        List<product> category = productService.findAll();
        //Then
        List<product> find = productRepository.findAll();
        assertEquals(pro.getPro_name(), find.get(0).getPro_name());
    }

    @Test
    void findName() throws Exception{
        //given
        product pro = new product();
        pro.name("고양이 장난감");
        //when
        List<product> name = productService.findName(pro.getPro_name());
        //Then
        product find = productRepository.findByName(name.get(0).getPro_name()).get(0);
        assertEquals(pro.getPro_name(), find.getPro_name());
    }
}