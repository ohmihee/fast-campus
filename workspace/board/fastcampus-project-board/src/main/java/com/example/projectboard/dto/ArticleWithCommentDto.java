package com.example.projectboard.dto;

import com.example.projectboard.domain.Article;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * DTO for {@link com.example.projectboard.domain.Article}
 */
public record ArticleWithCommentDto(Long id,
                                    UserAccountDto userAccountDto,
                                    Set<ArticleCommentDto> articleCommentDtos,
                                    String title,
                                    String contnet,
                                    String hasttag,
                                    LocalDateTime createdAt,
                                    String createdBy,
                                    LocalDateTime modifiedAt,
                                    String modifiedBy
) implements Serializable {
    public static ArticleWithCommentDto of (Long id,
                                            UserAccountDto userAccountDto,
                                            Set<ArticleCommentDto> articleCommentDtos,
                                            String title,
                                            String contnet,
                                            String hasttag,
                                            LocalDateTime createdAt,
                                            String createdBy,
                                            LocalDateTime modifiedAt,
                                            String modifiedBy) {
        return new ArticleWithCommentDto(id, userAccountDto, articleCommentDtos, title, contnet, hasttag, createdAt, createdBy, modifiedAt, modifiedBy);
    }
    public static ArticleWithCommentDto from(Article entity)
    {
        return new ArticleWithCommentDto(
                entity.getId(),
                UserAccountDto.from(entity.getUserAccount()),
                entity.getArticleComments().stream().map(ArticleCommentDto::from).collect(Collectors.toCollection(LinkedHashSet::new)),
                entity.getTitle(),
                entity.getContent(),
                entity.getHashtag(),
                entity.getCreatedAt(),
                entity.getCreatedBy(),
                entity.getModifiedAt(),
                entity.getModifiedBy()
        );
    }}