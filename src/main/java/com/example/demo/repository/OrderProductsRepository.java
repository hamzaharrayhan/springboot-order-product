package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.OrderProducts;

@Repository
public interface OrderProductsRepository extends JpaRepository<OrderProducts, Long> {
}