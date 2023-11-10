package org.example;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter @Getter
public class Product {
    private long id;
    private String name;
    private double price;
    private String description;

    public Product(Product product){
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.description = product.getDescription();
    }
}
