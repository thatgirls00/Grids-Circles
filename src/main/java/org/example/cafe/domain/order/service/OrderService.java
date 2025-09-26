package org.example.cafe.domain.order.service;

import lombok.RequiredArgsConstructor;
import org.example.cafe.domain.member.entity.Member;
import org.example.cafe.domain.member.service.MemberService;
import org.example.cafe.domain.order.dto.request.OrderRequestDto;
import org.example.cafe.domain.order.entity.Order;
import org.example.cafe.domain.order.repository.OrderRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberService memberService;

    public Order createOrder(OrderRequestDto requestDto) {

        Member member = memberService.registerOrFindMember(requestDto.getEmail());

        Order order = Order.createOrder(
                member,
                requestDto.getAddress(),
                requestDto.getPostalCode()
        );

        return orderRepository.save(order);


    }
}
