package com.ff.furry_friend.service;

import com.ff.furry_friend.entity.product;
import com.ff.furry_friend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

//    public List<product> findAll(Integer category) {
//        return productRepository.findAllByCategory(category);
//    }
//    public Optional<product> findOne(Integer category) {
//        return productRepository.findByName(category);
//    }
}
