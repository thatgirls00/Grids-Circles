package org.example.cafe.domain.order.dto.request;

import lombok.Getter;

@Getter
public class OrderProductRequestDto {
    private Long productId;
    private int quantity;
}