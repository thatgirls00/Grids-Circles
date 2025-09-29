package org.example.cafe.domain.product.entity;

import jakarta.persistence.*;
import lombok.*;
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

    /**
     * CoffeeImg 엔티티 생성을 위한 정적 팩토리 메서드
     * @param product 연결될 Product 엔티티
     * @param title 이미지 제목 (일반적으로 파일명 사용)
     * @param url 이미지의 웹 접근 URL (DB 저장용)
     * @return 생성된 CoffeeImg 엔티티
     */
    public static CoffeeImg createImageEntity(Product product, String title, String url) {
        return CoffeeImg.builder()
                .coffee(product)
                .title(title)
                .url(url)
                // createdAt은 @CreationTimestamp에 의해 자동 설정됨
                .build();
    }

    public void setProduct(Product product) {
        this.coffee = product;
    }
}