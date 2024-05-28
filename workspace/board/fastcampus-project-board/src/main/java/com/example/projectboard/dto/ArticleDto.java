package com.example.projectboard.dto;

import com.example.projectboard.domain.Article;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link com.example.projectboard.domain.Article}
 */
public record ArticleDto(LocalDateTime createdAt, String createdBy, String title, String content,
                         String hashtag) {
    public static ArticleDto of(LocalDateTime createdAt, String createdBy, String title, String content, String hashtag) {
        return new ArticleDto(createdAt, createdBy, title,content, hashtag);
    }
    //
    public static ArticleDto from(Article entity) {
        return new ArticleDto(null, "", "","","");
    }

    public Article toEntity () {
        return Article.of("","","");
    }
}