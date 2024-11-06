package com.example.rest_api_test.domain.member.dto;

import com.example.rest_api_test.domain.member.entity.Member;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MemberDTO {
    private Long id;
    private String username;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public MemberDTO(Member member) {
        this.id = member.getId();
        this.username = member.getUsername();
        this.createdDate = member.getCreatedDate();
        this.modifiedDate = member.getModifiedDate();
    }
}
