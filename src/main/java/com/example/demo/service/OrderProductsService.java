package com.example.demo.service;

import com.example.demo.dto.AddToCartDTO;
import com.example.demo.dto.OrdersDTO;

public interface OrderProductsService {
    OrdersDTO addMultipleItemsToCart(AddToCartDTO request);
}
