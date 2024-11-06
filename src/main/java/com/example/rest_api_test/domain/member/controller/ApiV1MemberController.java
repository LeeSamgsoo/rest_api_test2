package com.example.rest_api_test.domain.member.controller;

import com.example.rest_api_test.domain.member.dto.MemberDTO;
import com.example.rest_api_test.domain.member.dto.request.MemberRequest;
import com.example.rest_api_test.domain.member.dto.response.MemberResponse;
import com.example.rest_api_test.domain.member.entity.Member;
import com.example.rest_api_test.domain.member.service.MemberService;
import com.example.rest_api_test.global.rs_data.RsData;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
@Tag(name = "ApiV1MemberController", description = "회원 CRUD API")
public class ApiV1MemberController {
    private final MemberService memberService;

    @PostMapping("/join")
    public RsData<MemberResponse> join(@Valid @RequestBody MemberRequest memberRequest) {
        return RsData.of("200", "회원가입 성공",
                new MemberResponse(
                        this.memberService.create(memberRequest.getUsername(), memberRequest.getPassword())
                )
        );
    }

    @PostMapping("/login")
    public RsData<MemberResponse> login(@Valid @RequestBody MemberRequest memberRequest,
                                        HttpServletResponse res) {
        Member member = this.memberService.getMember(memberRequest.getUsername());
        if (member == null) {
            return RsData.of("500", "로그인 실패");
        }

        Cookie cookie = new Cookie("username", member.getUsername());
        cookie.setMaxAge(60 * 60);
        cookie.setPath("/");
        res.addCookie(cookie);

        return RsData.of("200", "로그인 성공",
                new MemberResponse(new MemberDTO(member))
        );
    }

    @GetMapping("/logout")
    public RsData<MemberResponse> logout(HttpServletResponse res) {
        Cookie cookie = new Cookie("username", null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        res.addCookie(cookie);

        return RsData.of("200", "로그아웃 성공");
    }
}
