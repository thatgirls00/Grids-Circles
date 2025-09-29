package org.example.cafe.domain.member.service;

import lombok.RequiredArgsConstructor;
import org.example.cafe.domain.member.entity.Member;
import org.example.cafe.domain.member.entity.Role;
import org.example.cafe.domain.member.repository.MemberRepository;
import org.example.cafe.domain.order.dto.reponse.OrderResponseDto;
import org.example.cafe.global.error.CustomException;
import org.example.cafe.global.error.ErrorCode;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    /**
     * 이메일 입력 시
     * 기존 회원일 경우: 기존 member return
     * 회원이 아닐 경우: memberRepository 에 save
     */

    public Member registerOrFindMember(String email) {
        return memberRepository.findByEmail(email)
                .orElseGet(() -> memberRepository.save(Member.createNew(email)));
    }

    public List<OrderResponseDto> getOrdersByEmail(String email) {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));

        // member.getOrders() → Order → OrderResponseDto 변환
        return member.getOrders().stream()
                .map(OrderResponseDto::fromEntity)
                .collect(Collectors.toList());
    }
}
