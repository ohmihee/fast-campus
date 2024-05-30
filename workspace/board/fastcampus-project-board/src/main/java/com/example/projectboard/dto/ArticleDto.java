package com.example.projectboard.dto;

import com.example.projectboard.domain.Article;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link com.example.projectboard.domain.Article}
 */
public record ArticleDto(
        Long id,
        UserAccountDto userAccountDto,
        String title,
        String content,
        String hashtag,
        LocalDateTime createAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy) {
    public static ArticleDto of(
            Long id,
            UserAccountDto userAccountDto,
            String title,
            String content,
            String hashtag,
            LocalDateTime createdAt,
            String createdBy,
            LocalDateTime modifiedAt,
            String modifiedBy
    ) {
        return new ArticleDto(id, userAccountDto, title,content, hashtag, createdAt, createdBy, modifiedAt, modifiedBy);
    }
    //
    public static ArticleDto from(Article entity) {
        return new ArticleDto(entity.getId(), null, "", "","",null, "", null, "");
    }

    public Article toEntity () {
        return Article.of("","","");
    }
}