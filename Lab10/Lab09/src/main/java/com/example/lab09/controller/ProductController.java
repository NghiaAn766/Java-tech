package com.example.lab09.controller;

import com.example.lab09.dto.ProductDTO;
import com.example.lab09.entity.Product;
import com.example.lab09.exception.NotFoundException;
import com.example.lab09.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping({"", "/"})
    public ResponseEntity<?> getProductList(){
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable("id") Long id) throws Exception {
        return ResponseEntity.ok(productService.getProduct(id));
    }

    // "/api/products/search?priceArrangeFrom=&priceArrangeTo=&brand=&color=
    @GetMapping("/search")
    public ResponseEntity<?> searchProduct(@RequestParam(value = "priceArrangeFrom", required = false, defaultValue = "-1") double priceArrangeFrom,
                                           @RequestParam(value = "priceArrangeTo", required = false, defaultValue = "-1") double priceArrangeTo,
                                           @RequestParam(value = "brand", required = false, defaultValue = "") List<String> brands,
                                           @RequestParam(value = "color", required = false, defaultValue = "") List<String> colors) {
        return ResponseEntity.ok(productService.getProductByRequest(priceArrangeFrom, priceArrangeTo, brands, colors));
    }

    @PostMapping("")
    public ResponseEntity<?> createProduct(@RequestBody ProductDTO productDTO) throws Exception {
        productService.save(productDTO);
        return ResponseEntity.ok("Product added successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Long id, @RequestBody ProductDTO updatedProduct) {
        try {
            productService.updateProduct(id, updatedProduct);
            return ResponseEntity.ok("Product updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating product");
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateProductInfo(@PathVariable Long id, @RequestBody Map<String, Object> payload){
        try {
            if (productService.getProduct(id) == null){
                return ResponseEntity.notFound().build();
            }

            Product updatedProduct = productService.getProduct(id);

            Object nameObj = payload.get("name");
            Object priceObj = payload.get("price");
            Object brandObj = payload.get("brand");
            Object colorObj = payload.get("color");

            if (nameObj != null) {
                updatedProduct.setName(payload.get("name").toString());
            }

            if (priceObj != null){
                updatedProduct.setPrice(((Number) priceObj).doubleValue());
            }

            if (brandObj != null){
                updatedProduct.setBrand(payload.get("brand").toString());
            }

            if (colorObj != null){
                updatedProduct.setColor(payload.get("color").toString());
            }

            productService.update(updatedProduct);
            return ResponseEntity.ok("Product updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating product");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        try{
            productService.delete(id);
            return ResponseEntity.ok("Product was deleted");
        } catch (NotFoundException ex){
            return ResponseEntity.notFound().build();
        }
    }
}
