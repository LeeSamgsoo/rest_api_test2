package com.example.rest_api_test.domain.article.service;

import com.example.rest_api_test.domain.article.dto.ArticleDTO;
import com.example.rest_api_test.domain.article.entity.Article;
import com.example.rest_api_test.domain.article.repository.ArticleRepository;
import com.example.rest_api_test.domain.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;

    public List<Article> readAll() {
        return this.articleRepository.findAll();
    }

    public Article readOne(Long id) {
        return this.articleRepository.findById(id).orElse(null);
    }

    public ArticleDTO create(Member member, String subject, String content) {
        Article article = Article.builder()
                .member(member)
                .subject(subject)
                .content(content)
                .build();
        this.articleRepository.save(article);
        return new ArticleDTO(article);
    }

    public ArticleDTO modify(Article article, String subject, String content) {
        Article modArticle = article.toBuilder()
                .subject(subject)
                .content(content)
                .build();
        this.articleRepository.save(modArticle);
        return new ArticleDTO(modArticle);
    }

    public ArticleDTO delete(Article article) {
        this.articleRepository.delete(article);
        return new ArticleDTO(article);
    }
}
