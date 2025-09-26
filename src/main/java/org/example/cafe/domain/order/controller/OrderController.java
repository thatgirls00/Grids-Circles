package org.example.cafe.domain.order.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.example.cafe.domain.order.dto.request.OrderRequestDto;
import org.example.cafe.domain.order.entity.Order;
import org.example.cafe.domain.order.service.OrderService;
import org.example.cafe.global.rsData.RsData;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<RsData<Order>> createOrder(@RequestBody OrderRequestDto requestOrder) {
        Order order = orderService.createOrder(requestOrder);

        return ResponseEntity.ok(
                new RsData<>("200", "주문 완료", order)
        );
    }
}
