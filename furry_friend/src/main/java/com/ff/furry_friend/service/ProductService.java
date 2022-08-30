package com.ff.furry_friend.repository.service;

import com.ff.furry_friend.entity.product;
import com.ff.furry_friend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class ProductService {

    @Autowired
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    public List<product> findCategory(Integer category) {
        return productRepository.findByCategoty(category);
    }

    public List<product> findName(String name) {
        return productRepository.findByName(name);
    }

    public List<product> findAll() {
        return productRepository.findAll();
    }


}
