package org.example.cafe.domain.member.controller;

import lombok.RequiredArgsConstructor;
import org.example.cafe.domain.member.dto.request.AdminLoginRequestDto;
import org.example.cafe.domain.member.entity.Member;
import org.example.cafe.domain.member.service.AdminAuthService;
import org.example.cafe.global.rsData.RsData;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/admin")
public class AdminAuthController {

    private final AdminAuthService adminAuthService;

    @PostMapping("/login")
    public ResponseEntity<RsData<Member>> login(@RequestBody AdminLoginRequestDto request) {
        Member admin = adminAuthService.login(request);
        return ResponseEntity.ok(new RsData<>("200", "로그인 성공", admin));
    }
}
