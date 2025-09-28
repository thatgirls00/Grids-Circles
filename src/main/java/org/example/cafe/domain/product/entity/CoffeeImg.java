package org.example.cafe.domain.product.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.cafe.domain.product.entity.Product;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "coffee_img")
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class CoffeeImg {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String url;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coffee_id")
    private Product coffee;

    @CreationTimestamp
    private LocalDateTime createdAt;

    // TODO: @getter 중복 코드입니다 !
    /*public String getUrl() {
        return url;
    }*/
}