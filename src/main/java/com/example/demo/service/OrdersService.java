package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.OrdersDTO;

public interface OrdersService {
    OrdersDTO placeOrder(Long orderId);
    List<OrdersDTO> getDraftOrders();
    OrdersDTO getDraftOrderById(Long orderId);
}
