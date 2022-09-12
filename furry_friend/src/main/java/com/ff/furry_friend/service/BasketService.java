package com.ff.furry_friend.service;

import com.ff.furry_friend.entity.basket;
import com.ff.furry_friend.repository.BasketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@Service
public class BasketService {

    @Autowired
    private final BasketRepository basketRepository;

    public BasketService(BasketRepository basketRepository) {
        this.basketRepository = basketRepository;
    }

    public String shopping(basket basket){
        validateDuplicate(basket);
        basketRepository.shopping(basket);
        return basket.getProduct().getPro_name();
    }

    private void validateDuplicate(basket ba) { //중복 상품 검증
        basketRepository.findByName(ba.getProduct().getPro_name())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 상품입니다.");
                });
    }

    public Optional<basket> findUserBasket(String id){
        Optional<basket> result = basketRepository.findUserBasket(id);
        return result;
    }

    public void DeleteBasket(Long pro_id, int id){
        basketRepository.DeleteBasket(pro_id, id);
    }
}
