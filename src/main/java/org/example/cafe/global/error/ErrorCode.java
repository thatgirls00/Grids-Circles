package org.example.cafe.global.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    // 관리자 인증 관련
    ADMIN_ACCOUNT_NOT_FOUND("A001", "관리자 계정을 찾을 수 없습니다."),
    ADMIN_ROLE_REQUIRED("A002", "관리자 권한이 없습니다."),
    ADMIN_PASSWORD_MISMATCH("A003", "비밀번호가 일치하지 않습니다."),


    EXAMPLE_ERROR_CODE("E001", "샘플 에러입니다.");

    private final String code;
    private final String message;
}
