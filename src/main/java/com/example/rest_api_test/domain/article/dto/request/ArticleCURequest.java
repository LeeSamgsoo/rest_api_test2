package com.example.rest_api_test.domain.article.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ArticleCURequest {
    @NotBlank
    private String subject;

    @NotBlank
    private String content;
}
