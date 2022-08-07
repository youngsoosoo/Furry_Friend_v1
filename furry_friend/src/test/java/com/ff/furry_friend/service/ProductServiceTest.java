package com.ff.furry_friend.service;

import com.ff.furry_friend.entity.basket;
import com.ff.furry_friend.entity.product;
import com.ff.furry_friend.repository.BasketRepository;
import com.ff.furry_friend.repository.MemoryProductRepository;
import com.ff.furry_friend.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
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
    @Autowired
    BasketRepository basketRepository;
    @Autowired
    BasketService basketService;


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
    void findAll() {
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
    void findName() {
        //given
        product pro = new product();
        pro.name("고양이 장난감");
        //when
        List<product> name = productService.findName(pro.getPro_name());
        //Then
        product find = productRepository.findByName(name.get(0).getPro_name()).get(0);
        assertEquals(pro.getPro_name(), find.getPro_name());
    }

    @Test
    void shopping() throws Exception{
        //Given
        basket ba = new basket();
        ba.setPro_name("고양이 장난감");
        //When
        basket ba1 = basketService.shopping(ba);
        //Then
        basket find = basketRepository.findById(saveId).get();
        assertEquals(ba.getPro_name(), find.getName());
    }
}