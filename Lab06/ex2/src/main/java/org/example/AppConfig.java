package org.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class AppConfig {

    @Bean
    public Product product() {
        return new Product();
    }

    @Bean
    @Scope("prototype")
    public Product product1() {
        Product product = new Product();
        product.setName("Samsung");
        return product;
    }

    @Bean
    @Scope("prototype")
    public Product product2(Product product1) {
        return new Product(product1);
    }

    @Bean
    @Scope("singleton")
    public Product product3() {
        Product product = new Product();
        product.setName("Xiaomi");
        return product;
    }
}
