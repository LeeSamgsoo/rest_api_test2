package com.example.rest_api_test.domain.member.service;

import com.example.rest_api_test.domain.member.dto.MemberDTO;
import com.example.rest_api_test.domain.member.entity.Member;
import com.example.rest_api_test.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public Member getMember(String username) {
        return this.memberRepository.findByUsername(username).orElse(null);
    }

    public MemberDTO create(String username, String password) {
        Member member = Member.builder()
                .username(username)
                .password(password)
                .build();
        this.memberRepository.save(member);
        return new MemberDTO(member);
    }
}
