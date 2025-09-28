package org.example.cafe.domain.member.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminLoginRequestDto {
    private String email;
    private String password;
}
