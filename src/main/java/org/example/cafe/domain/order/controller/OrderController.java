package org.example.cafe.domain.order.controller;

import lombok.RequiredArgsConstructor;
import org.example.cafe.domain.order.dto.reponse.OrderResponseDto;
import org.example.cafe.domain.order.dto.request.OrderRequestDto;
import org.example.cafe.domain.order.entity.Order;
import org.example.cafe.domain.order.service.OrderService;
import org.example.cafe.global.rsData.RsData;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<RsData<OrderResponseDto>> createOrder(@RequestBody OrderRequestDto requestOrder) {
        OrderResponseDto order = orderService.createOrder(requestOrder);

        return ResponseEntity.ok(
                new RsData<>("200", "주문 완료", order)
        );
    }
}
