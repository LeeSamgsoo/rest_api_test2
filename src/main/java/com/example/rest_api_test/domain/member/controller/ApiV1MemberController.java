package com.example.rest_api_test.domain.member.controller;

import com.example.rest_api_test.domain.article.service.ArticleService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/articles")
@Tag(name = "ApiV1MemberController", description = "회원 CRUD API")
public class ApiV1MemberController {
    private final ArticleService articleService;
}
