package com.example.rest_api_test.domain.member.service;

import com.example.rest_api_test.domain.article.repository.ArticleRepository;
import com.example.rest_api_test.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
}
