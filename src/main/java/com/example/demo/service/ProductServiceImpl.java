package com.example.demo.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ProductDTO;
import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Page<ProductDTO> getAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable)
                .map(product -> new ProductDTO(
                        product.getId(),
                        product.getName(),
                        product.getType(),
                        product.getPrice(),
                        product.getStock()
                ));
    }

    @Override
    public ProductDTO getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        return new ProductDTO(product.getId(), product.getName(), product.getType(), product.getPrice(), product.getStock());
    }

    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {
        Product product = new Product(null, productDTO.getName(), productDTO.getType(), productDTO.getPrice(), productDTO.getStock());
        product = productRepository.save(product);
        return new ProductDTO(product.getId(), product.getName(), product.getType(), product.getPrice(), product.getStock());
    }

    @Override
    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        product.setName(productDTO.getName());
        product.setType(productDTO.getType());
        product.setPrice(productDTO.getPrice());
        product.setStock(productDTO.getStock());
        product = productRepository.save(product);
        return new ProductDTO(product.getId(), product.getName(), product.getType(), product.getPrice(), product.getStock());
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
