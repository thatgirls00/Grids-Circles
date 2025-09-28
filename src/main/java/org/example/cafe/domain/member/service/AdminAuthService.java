package org.example.cafe.domain.member.service;

import lombok.RequiredArgsConstructor;
import org.example.cafe.domain.member.dto.request.AdminLoginRequestDto;
import org.example.cafe.domain.member.entity.Member;
import org.example.cafe.domain.member.entity.Role;
import org.example.cafe.domain.member.repository.MemberRepository;
import org.example.cafe.global.error.CustomException;
import org.example.cafe.global.error.ErrorCode;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminAuthService {

    private final MemberRepository memberRepository;

    public Member login(AdminLoginRequestDto requestDto) {
        Member admin = memberRepository.findByEmail(requestDto.getEmail())
                .orElseThrow(() -> new CustomException(ErrorCode.ADMIN_ACCOUNT_NOT_FOUND));

        /** role: 권한 확인 */
        if (admin.getRole() != Role.ADMIN) {
            throw new CustomException(ErrorCode.ADMIN_ROLE_REQUIRED);
        }

        /** password: 관리자 비밀번호 확인 */
        if (!requestDto.getPassword().equals(admin.getPassword())) {
            throw new CustomException(ErrorCode.ADMIN_PASSWORD_MISMATCH);
        }

        return admin;
    }
}
