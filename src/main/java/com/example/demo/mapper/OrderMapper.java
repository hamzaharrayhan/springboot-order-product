package com.example.demo.mapper;

import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.demo.dto.OrderProductsDTO;
import com.example.demo.dto.OrdersDTO;
import com.example.demo.model.OrderProducts;
import com.example.demo.model.Orders;

public class OrderMapper {

    public static OrdersDTO toDTO(Orders order) {

        return new OrdersDTO(
                order.getId(),
                order.getAddress(),
                order.getCustomer(),
                Optional.ofNullable(order.getOrderProducts()) // Handle null safely
                .orElse(Collections.emptyList()) // Return an empty list if null
                .stream()
                .map(OrderMapper::toOrderProductsDTO)
                .collect(Collectors.toList()),
                order.getStatus().name()
        );
    }

    private static OrderProductsDTO toOrderProductsDTO(OrderProducts orderProducts) {
        return new OrderProductsDTO(
                orderProducts.getProduct().getId(),
                orderProducts.getProduct().getName(),
                orderProducts.getProduct().getType(),
                orderProducts.getProduct().getPrice(),
                orderProducts.getQuantity(),
                orderProducts.getProduct().getPrice() * orderProducts.getQuantity()
        );
    }
}