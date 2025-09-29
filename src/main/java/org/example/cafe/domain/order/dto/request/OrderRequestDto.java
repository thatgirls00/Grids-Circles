package org.example.cafe.domain.order.dto.request;

import lombok.Getter;
import org.example.cafe.domain.product.dto.ProductDto;

import java.util.ArrayList;
import java.util.List;

@Getter
public class OrderRequestDto {

    private String email;
    private String address;
    private String postalCode;
    private List<OrderProductRequestDto> products = new ArrayList<>();
/*
    private List<ProductDto> products = new ArrayList<>();
*/
}
