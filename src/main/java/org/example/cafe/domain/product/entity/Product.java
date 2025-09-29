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

    // TODO: @getter 중복 코드입니다 !
    /*public String getDescription() {
        return description;
    }

    public List<CoffeeImg> getImages() {
        return images;
    }*/

    /**
     * 양방향 연관 관계 설정을 위한 편의 메서드
     * Product에 CoffeeImg를 추가하고, CoffeeImg에도 Product를 설정합니다.
     * @param image Product에 연결할 CoffeeImg 엔티티
     */
    public void addImage(CoffeeImg image) {
        this.images.add(image);

        // 무한 루프 방지 조건문 추가
        if (image.getCoffee() != this) {
            image.setCoffee(this);
        }
    }

    public void removeImage(CoffeeImg image) {
        this.images.remove(image);
        image.setProduct(null); // 양방향 관계 해제
        // @OneToMany에 CascadeType.ALL 또는 orphanRemoval=true 설정 시 DB에서 자동 삭제
    }
}