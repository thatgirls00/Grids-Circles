package org.example.cafe.domain.product.service;

import org.example.cafe.domain.product.entity.Product;
import org.example.cafe.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;


    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product create(String name, int price, int quantity) {
        Product product = new Product(name, price, quantity);

        return productRepository.save(product);
    }

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    public void modify(Product product, String name, int price, int quantity) {
        product.update(name, price, quantity);
    }

    public void delete(Product product) {
        productRepository.delete(product);
    }
}