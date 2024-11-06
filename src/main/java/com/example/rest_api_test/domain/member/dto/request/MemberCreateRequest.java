package com.example.rest_api_test.domain.member.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class MemberCreateRequest {
    @NotBlank
    private String username;

    @NotBlank
    private String password;
}
