package com.ff.furry_friend;

import com.ff.furry_friend.repository.BasketRepository;
import com.ff.furry_friend.repository.MemoryBasketRepository;
import com.ff.furry_friend.repository.MemoryProductRepository;
import com.ff.furry_friend.repository.ProductRepository;
import com.ff.furry_friend.service.BasketService;
import com.ff.furry_friend.service.ProductService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {
    private final DataSource dataSource;
    private final EntityManager em;
    public SpringConfig(DataSource dataSource, EntityManager em) {
        this.dataSource = dataSource;
        this.em = em;
    }
    @Bean
    public ProductService productService() {
        return new ProductService(productRepository());
    }
    @Bean
    public ProductRepository productRepository() {
        return new MemoryProductRepository(em);
    }

    @Bean
    public BasketService basketService() {
        return new BasketService(basketRepository());
    }
    @Bean
    public BasketRepository basketRepository(){
        return new MemoryBasketRepository(em);
    }
}
