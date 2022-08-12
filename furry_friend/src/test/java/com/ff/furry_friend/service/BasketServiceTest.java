package com.ff.furry_friend.service;

import com.ff.furry_friend.entity.basket;
import com.ff.furry_friend.entity.product;
import com.ff.furry_friend.entity.user;
import com.ff.furry_friend.repository.BasketRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class BasketServiceTest {
    @Autowired
    BasketRepository basketRepository;
    @Autowired
    BasketService basketService;

    @Test
    void shopping() throws Exception{
        //Given
        basket ba = new basket();
        product pro = new product();
        user user = new user();
        user.setId("root");
        pro.setPro_name("고양이 장난감");
        ba.setBasket_id(0L);
        ba.setProduct(pro);
        ba.setUser(user);
        ba.setAmount(1);
        //When
        String name = basketService.shopping(ba);
        //Then
        basket find = basketRepository.findByName(name).get();
        assertEquals(ba.getProduct().getPro_name(), find.getProduct().getPro_name());
    }

    @Test
    void createTest() {

        // given
        String username = "James";
        user user = com.ff.furry_friend.entity.user.builder()
                .name(username)
                .build();

        basket ba = new basket();
        product pro = new product();
        basketRepository.shopping(ba);

        List<basket> basketList = new ArrayList<>();

        for (int i = 1; i <= 2; i++) {
            ba = basket.builder()
                    .product(pro)
                    .user(user)
                    .build();

            basketList.add(ba);
        }

        // when
        basket savedbasket = basketRepository.shopping(ba);

        //then
        assertEquals(username, savedbasket.getProduct());
        assertEquals(username, savedbasket.getUser());
    }
}
