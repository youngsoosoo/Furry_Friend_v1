package com.ff.furry_friend.service;

import com.ff.furry_friend.entity.basket;
import com.ff.furry_friend.repository.BasketRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
        ba.setPro_name("고양이 장난감");
        ba.setId("1234");
        ba.setAmount(1);
        //When
        String name = basketService.shopping(ba);
        //Then
        basket find = basketRepository.findByName(name).get();
        assertEquals(ba.getPro_name(), find.getPro_name());
    }
}
