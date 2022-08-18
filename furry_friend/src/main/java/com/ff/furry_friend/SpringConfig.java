package com.ff.furry_friend;

import com.ff.furry_friend.repository.*;
import com.ff.furry_friend.repository.service.BasketService;
import com.ff.furry_friend.repository.service.ProductService;
import com.ff.furry_friend.repository.service.UserService;
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

    public ProductService productService() {
        return new ProductService(productRepository());
    }
    @Bean
    public ProductRepository productRepository() {
        return new MemoryProductRepository(em);
    }


    public BasketService basketService() {
        return new BasketService(basketRepository());
    }
    @Bean
    public BasketRepository basketRepository(){
        return new MemoryBasketRepository(em);
    }


    public UserService userService(){
        return new UserService(userRepository());
    }

    @Bean
    public UserRepository userRepository(){
        return new MemoryUserRepository(em);
    }
}
