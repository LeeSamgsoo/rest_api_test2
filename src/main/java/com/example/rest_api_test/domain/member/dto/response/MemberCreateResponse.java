package com.example.rest_api_test.domain.member.dto.response;

import com.example.rest_api_test.domain.member.dto.MemberDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class MemberCreateResponse {
    private Long id;
    private String username;
    private LocalDateTime createdDate;

    public MemberCreateResponse(MemberDTO memberDTO) {
        this.id = memberDTO.getId();;
        this.username = memberDTO.getUsername();
        this.createdDate = memberDTO.getCreatedDate();
    }
}
