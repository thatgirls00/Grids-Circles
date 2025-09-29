package org.example.cafe.domain.order.dto.reponse;

import lombok.Builder;
import lombok.Getter;
import org.example.cafe.domain.order.entity.Order;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
public class OrderResponseDto {
    private Long orderId;
    private String email;
    private String address;
    private String postalCode;
    private int totalPrice;
    private String orderState;
    private List<OrderItemResponseDto> items;

    public static OrderResponseDto fromEntity(Order order) {
        return OrderResponseDto.builder()
                .orderId(order.getId())
                .email(order.getMember().getEmail())
                .address(order.getAddress())
                .postalCode(order.getPostalCode())
                .orderState(order.getOrderState().name())
                .totalPrice(order.getTotalPrice())
                .items(order.getOrderItems().stream()
                        .map(OrderItemResponseDto::fromEntity)
                        .collect(Collectors.toList()))
                .build();
    }
}
