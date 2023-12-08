package com.example.lab09.service;

import org.springframework.stereotype.Service;

@Service
public interface OrderDetailService {
    void deleteAllByOrderId(Long orderId);
}
