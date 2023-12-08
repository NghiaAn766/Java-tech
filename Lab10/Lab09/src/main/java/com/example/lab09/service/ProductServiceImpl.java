package com.example.lab09.service;

import com.example.lab09.dto.ProductDTO;
import com.example.lab09.entity.Order;
import com.example.lab09.entity.Product;
import com.example.lab09.exception.NotFoundException;
import com.example.lab09.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductServiceImpl implements ProductService{
    @Autowired
    ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProduct(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product not found"));
    }

    private boolean checkProductPrice(double priceFrom, double priceTo, Product product) {
        return priceFrom < 0 || (product.getPrice() >= priceFrom && (priceTo < 0 || product.getPrice() <= priceTo));
    }

    private boolean checkProductBrand(List<String> brands, Product product) {
        if (brands.isEmpty()) {
            return true;
        }

        return brands.stream()
                .anyMatch(brand -> product.getBrand().toLowerCase().contains(brand.toLowerCase()));
    }

    private boolean checkProductColor(List<String> colors, Product product) {
        if (colors.isEmpty()) {
            return true;
        }

        return colors.stream()
                .anyMatch(color -> product.getColor().toLowerCase().contains(color.toLowerCase()));
    }

    @Override
    public List<Product> getProductByRequest(double priceArrangeFrom, double priceArrangeTo, List<String> brands, List<String> colors) {
        List<Product> result = new ArrayList<>();
        for (Product product : productRepository.findAll()) {
            if (checkProductPrice(priceArrangeFrom, priceArrangeTo, product) &&
                    checkProductBrand(brands, product) &&
                    checkProductColor(colors, product)) {
                result.add(product);
            }
        }

        return result;
    }

    @Override
    public void save(ProductDTO productDTO) {
        Product product = new Product();
        productRepository.save(mapToEntity(productDTO, product));
    }

    @Override
    public void update(Product product) {
        productRepository.save(product);
    }

    @Override
    public void updateProduct(Long id, ProductDTO productDTO) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        product.setName(productDTO.getName());
        product.setBrand(productDTO.getBrand());
        product.setColor(productDTO.getColor());
        product.setPrice(productDTO.getPrice());
        productRepository.save(product);
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    private Product mapToEntity(ProductDTO productDTO, Product product){
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setBrand(productDTO.getBrand());
        product.setColor(productDTO.getColor());

        return product;
    }
}
