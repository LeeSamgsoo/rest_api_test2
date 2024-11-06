package com.example.rest_api_test.domain.article.dto.response;

import com.example.rest_api_test.domain.article.dto.ArticleDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ArticleResponse {
    private final ArticleDTO article;
}
