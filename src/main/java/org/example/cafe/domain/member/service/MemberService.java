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

    public Member registerOrFindMember(String email) {
        return memberRepository.findByEmail(email)
                .orElseGet(() -> memberRepository.save(Member.createNew(email)));
    }
}
