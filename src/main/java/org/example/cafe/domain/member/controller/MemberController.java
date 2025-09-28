package org.example.cafe.domain.member.controller;

import lombok.RequiredArgsConstructor;
import org.example.cafe.domain.member.entity.Member;
import org.example.cafe.domain.member.service.MemberService;
import org.example.cafe.domain.order.dto.reponse.OrderResponseDto;
import org.example.cafe.global.rsData.RsData;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/members")
public class MemberController {

    private final MemberService memberService;

    /**
     * 회원 주문 내역 조회
     */
    @GetMapping("/{email}/orders")
    public ResponseEntity<RsData<List<OrderResponseDto>>> getOrdersByEmail(
            @PathVariable String email)
    {
        List<OrderResponseDto> orders = memberService.getOrdersByEmail(email);

        return ResponseEntity.ok(new RsData<>("200", "주문 내역 조회 성공", orders));
    }

}
