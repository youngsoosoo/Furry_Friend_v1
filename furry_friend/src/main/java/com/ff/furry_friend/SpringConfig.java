package com.ff.furry_friend;

import com.ff.furry_friend.repository.MemoryProductRepository;
import com.ff.furry_friend.repository.ProductRepository;
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
    public ProductService productServicee() {
        return new ProductService(productRepository());
    }
    @Bean
    public ProductRepository productRepository() {
        return new MemoryProductRepository(em);
    }
}
