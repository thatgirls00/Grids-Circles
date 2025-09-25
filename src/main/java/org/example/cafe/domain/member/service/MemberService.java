package org.example.cafe.domain.member.service;

import lombok.RequiredArgsConstructor;
import org.example.cafe.domain.member.entity.Member;
import org.example.cafe.domain.member.entity.Role;
import org.example.cafe.domain.member.repository.MemberRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    /**
     * 이메일 입력 시
     * 기존 회원일 경우: 기존 member return
     * 회원이 아닐 경우: memberRepository 에 save
     */

    public Member registerOrFindMember(Member member) {
        return memberRepository.findByEmail(member.getEmail())
                .orElseGet(() -> {
                        member.setRole(Role.USER); //기본값 user
                        return memberRepository.save(member);
                });
    }
}
