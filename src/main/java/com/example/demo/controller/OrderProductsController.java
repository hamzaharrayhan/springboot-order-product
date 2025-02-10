package com.example.demo.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.AddToCartDTO;
import com.example.demo.dto.OrdersDTO;
import com.example.demo.service.OrderProductsService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class OrderProductsController {

    private final OrderProductsService orderProductsService;

    @PostMapping("/add")
    public OrdersDTO addProductsToCart(@RequestBody AddToCartDTO request) {
        return orderProductsService.addMultipleItemsToCart(request);
    }
}
