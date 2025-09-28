package org.example.cafe.domain.product.dto.admin;

import lombok.Builder;
import lombok.Getter;
import org.example.cafe.domain.product.entity.Product;

@Getter
@Builder
public class AdminProductResponseDto {
    private Long id;
    private String name;
    private int price;
    private int quantity;
    private String description;

    public static AdminProductResponseDto fromEntity(Product product) {
        return AdminProductResponseDto.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .description(product.getDescription())
                .build();
    }
}
