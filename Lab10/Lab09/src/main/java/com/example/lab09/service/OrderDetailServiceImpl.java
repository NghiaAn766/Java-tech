package com.example.lab09.service;

import com.example.lab09.repository.OrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderDetailServiceImpl implements OrderDetailService{
    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Override
    public void deleteAllByOrderId(Long orderId) {
        orderDetailRepository.deleteAllByOrderId(orderId);
    }
}
