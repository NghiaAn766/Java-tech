package com.example.lab09.service;

import com.example.lab09.dto.OrderDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {
    List<OrderDTO> getAllOrders();

    OrderDTO getOrder(Long id) throws Exception;

    void createOrder(Long accountId, Long productId, int quantity) throws Exception;

    void update(Long id, double totalPrice);

    void delete(Long id);
}
