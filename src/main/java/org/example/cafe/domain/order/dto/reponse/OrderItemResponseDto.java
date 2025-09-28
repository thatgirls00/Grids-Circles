package org.example.cafe.domain.order.dto.reponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.example.cafe.domain.order.entity.OrderItem;

@Getter
@Builder
@AllArgsConstructor
public class OrderItemResponseDto {
    private Long productId;
    private String productName;
    private int quantity;
    private int price;

    public static OrderItemResponseDto fromEntity(OrderItem orderItem) {
        return OrderItemResponseDto.builder()
                .productId(orderItem.getProduct().getId())
                .productName(orderItem.getProduct().getName())
                .price(orderItem.getProduct().getPrice())
                .quantity(orderItem.getQuantity())
                .build();
    }
}