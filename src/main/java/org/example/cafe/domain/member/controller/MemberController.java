package org.example.cafe.domain.member.controller;

import lombok.RequiredArgsConstructor;
import org.example.cafe.domain.member.entity.Member;
import org.example.cafe.domain.member.service.MemberService;
import org.example.cafe.global.rsData.RsData;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/members")
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<RsData<Member>> registerOrFindMember(@RequestBody Member requestMember) {
        Member member = memberService.registerOrFindMember(requestMember);
        return ResponseEntity.ok(new RsData<>("200", "회원 조회/등록 완료", member));
    }

}
