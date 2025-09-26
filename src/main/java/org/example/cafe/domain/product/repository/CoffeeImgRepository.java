package org.example.cafe.domain.product.repository;

import org.example.cafe.domain.product.entity.CoffeeImg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoffeeImgRepository extends JpaRepository<CoffeeImg, Long> {
}
