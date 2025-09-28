package org.example.cafe.domain.product.repository;

import org.example.cafe.domain.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT p FROM Product p LEFT JOIN FETCH p.images")
    List<Product> findAllWithImages();

    Optional<Product> findByName(String name);
}
