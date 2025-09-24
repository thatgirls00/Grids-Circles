package org.example.cafe.global.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    EXAMPLE_ERROR_CODE("E001", "샘플 에러입니다.");

    private final String code;
    private final String message;
}
