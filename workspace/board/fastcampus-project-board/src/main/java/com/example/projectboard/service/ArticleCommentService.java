package com.example.projectboard.service;

import com.example.projectboard.dto.ArticleCommentDto;
import com.example.projectboard.repository.ArticleCommentRepository;
import com.example.projectboard.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@RequiredArgsConstructor
@Service
public class ArticleCommentService {
    private final ArticleCommentRepository articleCommentRepository;
    private final ArticleRepository articleRepository;


    @Transactional(readOnly = true)
    public List<ArticleCommentDto> searchArticleComment(Long articleId) {
        return List.of();
    }
}
