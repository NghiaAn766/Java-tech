package org.example;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

@NoArgsConstructor
@Setter @Getter
public class Product {
    private long id;
    private String name;
    private double price;
    private String description;

    public Product(long id, String name, double price, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public Product(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.description = product.getDescription();
    }

    @Override
    public String toString(){
        return id + " - " + name + " - " + price + " - " + description;
    }
}
