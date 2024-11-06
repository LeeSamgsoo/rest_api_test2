package com.example.rest_api_test.domain.article.dto;

import com.example.rest_api_test.domain.article.entity.Article;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ArticleDTO {
    private Long id;
    private String subject;
    private String content;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private String author;

    public ArticleDTO(Article article) {
        this.id = article.getId();;
        this.subject = article.getSubject();
        this.content = article.getContent();
        this.createdDate = article.getCreatedDate();
        this.modifiedDate = article.getModifiedDate();
        this.author = article.getMember().getUsername();
    }
}
