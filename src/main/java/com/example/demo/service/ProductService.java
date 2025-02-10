package com.example.demo.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.demo.dto.ProductDTO;

public interface ProductService {
    Page<ProductDTO> getAllProducts(Pageable pageable);
    ProductDTO getProductById(Long id);
    ProductDTO createProduct(ProductDTO productDTO);
    ProductDTO updateProduct(Long id, ProductDTO productDTO);
    void deleteProduct(Long id);
}
