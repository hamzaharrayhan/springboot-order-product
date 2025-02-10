package com.example.demo.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrdersDTO {
        private Long id;
        private String address;
        private String customer;
        private List<OrderProductsDTO> orderProducts;
        private String status;
}
