package com.example.rest_api_test.domain.article.dto.response;

import com.example.rest_api_test.domain.article.dto.ArticleDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ArticlesResponse {
    private final List<ArticleDTO> articleList;
}
