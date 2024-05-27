package com.example.projectboard.dto;

import java.io.Serializable;

public record ArticleUdo(
        String title,
        String content,
        String hasgtag
)  {
    public static ArticleUdo of (String title, String content, String hashtag) {
        return new ArticleUdo(title,content,hashtag);
    }
}
