package org.example.cafe.domain.order.dto.request;

import lombok.Getter;

@Getter
public class OrderRequestDto {

    private String email;
    private String address;
    private String postalCode;

    // private OrderItem orderItem; TODO: 상품 추가 예정
    // private int totalPrice; TODO: 상품 추가 예정
}
