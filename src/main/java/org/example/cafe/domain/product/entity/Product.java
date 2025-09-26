package org.example.cafe.domain.product.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;         //상품번호
    private String name;    //상품명    notnull
    private int price;      //상풍가격  notnull
    private int quantity;   //상품수량  notnull

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
}