package com.example.projectboard.dto;

import com.example.projectboard.domain.ArticleComment;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link com.example.projectboard.domain.ArticleComment}
 */
public record ArticleCommentDto(
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy,
        String content
) implements Serializable {
    public static ArticleCommentDto of(LocalDateTime createdAt, String createdBy, LocalDateTime modifiedAt, String modifiedBy, String content) {
        return new ArticleCommentDto(createdAt, createdBy, modifiedAt, modifiedBy,content);
    }

    public static ArticleCommentDto from(ArticleComment articleComment) {
        return new ArticleCommentDto(
                articleComment.getCreatedAt(),
                articleComment.getCreatedBy(),
                articleComment.getModifiedAt(),
                articleComment.getModifiedBy(),
                articleComment.getContent()
        );
    }
}