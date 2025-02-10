package com.example.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.AddToCartDTO;
import com.example.demo.dto.OrderProductsDTO;
import com.example.demo.dto.OrdersDTO;
import com.example.demo.mapper.OrderMapper;
import com.example.demo.model.OrderProducts;
import com.example.demo.model.OrderStatus;
import com.example.demo.model.Orders;
import com.example.demo.model.Product;
import com.example.demo.repository.OrderProductsRepository;
import com.example.demo.repository.OrdersRepository;
import com.example.demo.repository.ProductRepository;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class OrderProductsServiceImpl implements OrderProductsService {

    private final OrderProductsRepository orderProductsRepository;
    private final OrdersRepository ordersRepository;
    private final ProductRepository productRepository;

    @Override
    @Transactional
    public OrdersDTO addMultipleItemsToCart(AddToCartDTO request) {
        Orders order;

        order = new Orders();
        order.setCustomer(request.getCustomer());
        order.setAddress(request.getAddress());
        order.setStatus(OrderStatus.DRAFT);
        order = ordersRepository.save(order);

        for (OrderProductsDTO itemDTO : request.getItems()) {
            Product product = productRepository.findById(itemDTO.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            OrderProducts orderProducts = new OrderProducts(null, order, product, itemDTO.getQuantity());
            orderProductsRepository.save(orderProducts);
        }

        return OrderMapper.toDTO(order);
    }
}
