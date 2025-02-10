package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderProductsDTO {
    private Long productId;
    private String productName;
    private String productType;
    private Double productPrice;
    private Integer quantity;
    private Double totalPrice;
}