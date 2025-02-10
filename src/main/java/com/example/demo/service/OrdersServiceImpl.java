package com.example.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.OrdersDTO;
import com.example.demo.mapper.OrderMapper;
import com.example.demo.model.OrderProducts;
import com.example.demo.model.OrderStatus;
import com.example.demo.model.Orders;
import com.example.demo.model.Product;
import com.example.demo.repository.OrdersRepository;
import com.example.demo.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrdersServiceImpl implements OrdersService {

    private final OrdersRepository ordersRepository;
    private final ProductRepository productRepository;

    @Override
    @Transactional
    public OrdersDTO placeOrder(Long orderId) {
        Orders order = ordersRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        if (order.getStatus() == OrderStatus.PLACED) {
            throw new RuntimeException("Order is already placed");
        }

        order.setStatus(OrderStatus.PLACED);
        ordersRepository.save(order);

        for (OrderProducts orderProducts : order.getOrderProducts()) {
            Product product = orderProducts.getProduct();
            product.setStock(product.getStock() - orderProducts.getQuantity());

            productRepository.save(product);
        }

        return OrderMapper.toDTO(order);
    }

    @Override
    public List<OrdersDTO> getDraftOrders() {
        List<Orders> draftOrders = ordersRepository.findByStatus(OrderStatus.DRAFT);

        return draftOrders.stream()
                .map(OrderMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public OrdersDTO getDraftOrderById(Long orderId) {
        Orders order = ordersRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        if (order.getStatus() != OrderStatus.DRAFT) {
            throw new RuntimeException("This order is already placed and cannot be edited.");
        }

        return OrderMapper.toDTO(order);
    }
}
