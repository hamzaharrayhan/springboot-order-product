package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.OrdersDTO;
import com.example.demo.service.OrdersService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrdersController {

    private final OrdersService orderService;

    @GetMapping("/drafts")
    public List<OrdersDTO> getDraftOrders() {
        return orderService.getDraftOrders();
    }

    @PostMapping("/{orderId}/place")
    public OrdersDTO placeOrder(@PathVariable Long orderId) {
        return orderService.placeOrder(orderId);
    }
}
