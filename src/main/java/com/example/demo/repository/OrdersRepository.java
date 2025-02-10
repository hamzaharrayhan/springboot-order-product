package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.OrderStatus;
import com.example.demo.model.Orders;

public interface OrdersRepository extends JpaRepository<Orders, Long> {

    public List<Orders> findByStatus(OrderStatus orderStatus);
}
