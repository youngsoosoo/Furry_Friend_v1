package com.ff.furry_friend.repository;

import com.ff.furry_friend.entity.product;
import org.springframework.stereotype.Repository;

import java.util.*;

public class MemoryProductRepository  {
    private static Map<Integer, product> store = new HashMap<>();

//    @Override
//    public Optional<product> findByName(Integer category) {
//        return store.values().stream()
//                .filter(product -> product.getCategory().equals(category))
//                .findAny();
//        //반복을 돌리면서 member.getName()이 파라미터로 넘어온 값이랑 같은지 확인하는 것 끝까지 돌려서 없으면 Optional에 감싸서 반환
//    }
//
//    @Override
//    public List<product> findAll() {
//        return new ArrayList<>(store.values());//스토어에 있는 멤버들 반환
//    }
}
