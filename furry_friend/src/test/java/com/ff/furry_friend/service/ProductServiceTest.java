package com.ff.furry_friend.service;

import com.ff.furry_friend.entity.product;
import com.ff.furry_friend.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Optional;

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
        pro.setCategory(24);
        //when
        Optional<product> category = productService.findCategory(pro.getCategory());
        //Then
        product find = productRepository.findByCategoty(category.get().getCategory()).get();
        assertEquals(pro.getCategory(), find.getCategory());
    }

    @Test
    void findAll() {
        //given
        product pro = new product();
        pro.setPro_name("고양이 장난감");
        //when
        List<product> category = productService.findAll();
        //Then
        List<product> find = productRepository.findAll();
        assertEquals(pro.getPro_name(), find.get(0).getPro_name());
    }
}