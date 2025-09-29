package org.example.cafe.domain.order.service;

import lombok.RequiredArgsConstructor;
import org.example.cafe.domain.member.entity.Member;
import org.example.cafe.domain.member.service.MemberService;
import org.example.cafe.domain.order.dto.reponse.OrderResponseDto;
import org.example.cafe.domain.order.dto.request.OrderProductRequestDto;
import org.example.cafe.domain.order.dto.request.OrderRequestDto;
import org.example.cafe.domain.order.entity.Order;
import org.example.cafe.domain.order.entity.OrderItem;
import org.example.cafe.domain.order.repository.OrderRepository;
import org.example.cafe.domain.product.entity.Product;
import org.example.cafe.domain.product.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductService productService;
    private final MemberService memberService;

    public OrderResponseDto createOrder(OrderRequestDto requestDto) {

        Member member = memberService.registerOrFindMember(requestDto.getEmail());

        List<OrderItem> items = createOrderItems(requestDto);

        Order order = Order.createOrder(
                member,
                requestDto.getAddress(),
                requestDto.getPostalCode(),
                items
        );

        Order savedOrder = orderRepository.save(order);

        return OrderResponseDto.fromEntity(savedOrder);
    }

    private List<OrderItem> createOrderItems(OrderRequestDto requestDto) {
        List<OrderItem> items = new ArrayList<>();

        for (OrderProductRequestDto productDto : requestDto.getProducts()) {
            Product product = productService.getProductOrThrow(productDto.getProductId());

            OrderItem orderItem = OrderItem.createOrderItem(product, productDto.getQuantity());
            items.add(orderItem);
        }

        return items;
    }
}
