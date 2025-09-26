package org.example.cafe.domain.product.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;         // 상품번호

    private String name;     // 상품명 (Not Null)
    private int price;       // 상품 가격 (Not Null)
    private int quantity;    // 상품 수량 (Not Null)
    private String description;

    @OneToMany(mappedBy = "coffee", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<CoffeeImg> images = new ArrayList<>();

    public Product(String name, int price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public void update(String name, int price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public List<CoffeeImg> getImages() {
        return images;
    }
}