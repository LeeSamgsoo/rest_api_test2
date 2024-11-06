package com.example.rest_api_test.domain.article.controller;

import com.example.rest_api_test.domain.article.dto.ArticleDTO;
import com.example.rest_api_test.domain.article.dto.request.ArticleCURequest;
import com.example.rest_api_test.domain.article.dto.response.ArticleResponse;
import com.example.rest_api_test.domain.article.dto.response.ArticlesResponse;
import com.example.rest_api_test.domain.article.entity.Article;
import com.example.rest_api_test.domain.article.service.ArticleService;
import com.example.rest_api_test.domain.member.entity.Member;
import com.example.rest_api_test.domain.member.service.MemberService;
import com.example.rest_api_test.global.rs_data.RsData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/articles")
@Tag(name = "ApiV1ArticleController", description = "게시글 CRUD API")
public class ApiV1ArticleController {
    private final ArticleService articleService;
    private final MemberService memberService;

    @GetMapping("")
    @Operation(summary = "게시글 다건 조회")
    public RsData<ArticlesResponse> readArticles() {
        return RsData.of("200", "게시글 다건 조회 성공",
                new ArticlesResponse(this.articleService.readAll().stream().map(ArticleDTO::new).toList())
        );
    }

    @GetMapping("/{id}")
    @Operation(summary = "게시글 단건 조회")
    public RsData<ArticleResponse> readArticle(@PathVariable("id") Long id) {
        Article article = this.articleService.readOne(id);
        if (article == null) {
            return RsData.of("500", "%s번 게시글은 존재하지 않습니다.".formatted(id));
        }

        return RsData.of("200", "%s번 게시글 단건 조회 성공".formatted(id),
                new ArticleResponse(new ArticleDTO(article))
        );
    }

    @PostMapping("")
    @Operation(summary = "게시글 생성")
    public RsData<ArticleResponse> createArticle(@Valid @RequestBody ArticleCURequest articleCURequest,
                                                 HttpServletRequest req) {
        Cookie[] cookies = req.getCookies();
        String username = "";
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("username")) {
                username = cookie.getValue();
            }
        }

        Member member = this.memberService.getMember(username);
        if (member == null) {
            return RsData.of("500", "존재하지 않는 유저");
        }

        return RsData.of("200", "게시글 생성 성공",
                new ArticleResponse(this.articleService.create(
                        member, articleCURequest.getSubject(), articleCURequest.getContent()
                ))
        );
    }

    @PatchMapping("/{id}")
    @Operation(summary = "게시글 수정")
    public RsData<ArticleResponse> modifyArticle(@PathVariable("id") Long id,
                                                 @Valid @RequestBody ArticleCURequest articleCURequest,
                                                 HttpServletRequest req) {
        Cookie[] cookies = req.getCookies();
        String username = "";
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("username")) {
                username = cookie.getValue();
            }
        }
        Member member = this.memberService.getMember(username);
        if (member == null) {
            return RsData.of("500", "존재하지 않는 유저");
        }

        Article article = this.articleService.readOne(id);
        if (article == null) {
            return RsData.of("500", "%s번 게시글은 존재하지 않습니다.".formatted(id));
        }
        if (article.getMember() != member) {
            return RsData.of("500", "권한이 없습니다.");
        }

        return RsData.of(
                "200",
                "게시글 수정 성공",
                new ArticleResponse(
                        this.articleService.modify(article, articleCURequest.getSubject(), articleCURequest.getContent())
                )
        );

    }

    @DeleteMapping("/{id}")
    @Operation(summary = "게시글 삭제")
    public RsData<ArticleResponse> deleteArticle(@PathVariable("id") Long id, HttpServletRequest req) {
        Cookie[] cookies = req.getCookies();
        String username = "";
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("username")) {
                username = cookie.getValue();
            }
        }
        Member member = this.memberService.getMember(username);
        if (member == null) {
            return RsData.of("500", "존재하지 않는 유저");
        }

        Article article = this.articleService.readOne(id);
        if (article == null) {
            return RsData.of("500", "%s번 게시글은 존재하지 않습니다.".formatted(id));
        }
        if (article.getMember() != member) {
            return RsData.of("500", "권한이 없습니다.");
        }

        return RsData.of(
                "200",
                "게시글 삭제 성공",
                new ArticleResponse(this.articleService.delete(article))
        );
    }
}
