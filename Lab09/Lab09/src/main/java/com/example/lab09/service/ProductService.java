package com.example.lab09.service;

import com.example.lab09.dto.ProductDTO;
import com.example.lab09.entity.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    List<Product> getAllProducts();

    Product getProduct(Long id) throws Exception;

    List<Product> getProductByRequest(double priceArrangeFrom, double priceArrangeTo, List<String> brand, List<String> color);

    void save(ProductDTO productDTO);

    void update(Product product);

    void updateProduct(Long id, ProductDTO productDTO);

    void delete(Long id);
}
