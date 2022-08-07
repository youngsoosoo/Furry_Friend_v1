package com.ff.furry_friend.service;

import com.ff.furry_friend.entity.basket;
import com.ff.furry_friend.repository.BasketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class BasketService {

    @Autowired
    private final BasketRepository basketRepository;

    public BasketService(BasketRepository basketRepository) {
        this.basketRepository = basketRepository;
    }

    public basket shopping(basket basket){
        validateDuplicate(basket);
        return basketRepository.shopping(basket);
    }

    private void validateDuplicate(basket ba) {
        basketRepository.findByName(ba.getPro_name())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 상품입니다.");
                }); //optional로 바꿔보기
    }
}
