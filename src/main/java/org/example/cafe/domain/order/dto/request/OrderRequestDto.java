package org.example.cafe.domain.order.dto.request;

import lombok.Getter;

import java.util.List;

@Getter
public class OrderRequestDto {

    private String email;
    private String address;
    private String postalCode;
    private List<OrderProductRequestDto> products;
}
