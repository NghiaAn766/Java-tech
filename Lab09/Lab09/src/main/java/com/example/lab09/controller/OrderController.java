package com.example.lab09.controller;

import com.example.lab09.exception.NotFoundException;
import com.example.lab09.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping({"", "/"})
    public ResponseEntity<?> getOrders(){
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderById(@PathVariable("id") Long id) throws Exception {
        return ResponseEntity.ok(orderService.getOrder(id));
    }

    @PostMapping("")
    public ResponseEntity<?> createOrder(@RequestBody Map<String, Object> payload) throws Exception {
        Object accountIdObj = payload.get("accountId");
        Object productIdObj = payload.get("productId");
        Object quantityObj = payload.get("quantity");

        if (accountIdObj == null) {
            return ResponseEntity.badRequest().body("Missing 'accountId' in the request body.");
        }

        if (productIdObj == null) {
            return ResponseEntity.badRequest().body("Missing 'productId' in the request body.");
        }

        if (quantityObj == null) {
            return ResponseEntity.badRequest().body("Missing 'quantity' in the request body.");
        }

        Long accountId = ((Number) accountIdObj).longValue();
        Long productId = ((Number) productIdObj).longValue();
        int quantity = ((Number) quantityObj).intValue();

        orderService.createOrder(accountId, productId, quantity);
        return ResponseEntity.ok("Order added successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateOrder(@PathVariable Long id, @RequestBody Map<String, Object> payload) {
        Object totalPriceObj = payload.get("totalPrice");

        if (totalPriceObj == null) {
            return ResponseEntity.badRequest().body("Missing 'totalPrice' in the request body.");
        }

        double totalPrice = ((Number) totalPriceObj).doubleValue();
        orderService.update(id, totalPrice);
        return ResponseEntity.ok("Order updated successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable Long id) {
        try{
            orderService.delete(id);
            return ResponseEntity.ok("Order was deleted");
        } catch (NotFoundException ex){
            return ResponseEntity.notFound().build();
        }
    }
}
