package com.example.projectboard.service;

import com.example.projectboard.domain.Article;
import com.example.projectboard.domain.type.SearchType;
import com.example.projectboard.dto.ArticleDto;
import com.example.projectboard.dto.ArticleUdo;
import com.example.projectboard.dto.ArticleWithCommentDto;
import com.example.projectboard.repository.ArticleRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class ArticleService {
    private final ArticleRepository articleRepository;

    @Transactional(readOnly = true)
    public Page<ArticleDto> searchArticles(SearchType searchType, String searchKeyword, Pageable pageable) {

        if (StringUtils.isEmpty(searchKeyword)) {
            return articleRepository.findAll(pageable).map(ArticleDto::from);
        }
        return switch (searchType) {
            case TITLE -> articleRepository.findByTitleContaining(searchKeyword, pageable).map(ArticleDto::from);
            case CONTENT -> articleRepository.findByContentContaining(searchKeyword, pageable).map(ArticleDto::from);
            case ID -> articleRepository.findByUserAccount_UserIdContaining(searchKeyword, pageable).map(ArticleDto::from);
            case NICKNAME -> articleRepository.findByUserAccount_NicknameContaining(searchKeyword, pageable).map(ArticleDto::from);
            case HASHTAG -> articleRepository.findByHashtag("#"+searchKeyword, pageable).map(ArticleDto::from);
        };
    }

    @Transactional(readOnly = true)
    public ArticleWithCommentDto searchArticle(Long articleId) {
        return articleRepository.findById(articleId).map(ArticleWithCommentDto::from).orElseThrow(()->new EntityNotFoundException("게시글이 없습니다. - articleId:" +articleId));
    }

    public void saveArticle(ArticleDto dto) {
        articleRepository.save(dto.toEntity());


    }

    public void updateArticle(long articleId, ArticleUdo udo) {

    }

    public void updateArticle(ArticleDto dto) {
        try {
            Article article = articleRepository.getReferenceById(dto.id());
            article.setHashtag(dto.hashtag());
            article.setTitle(dto.title() == null ? article.getTitle() : dto.title());
            article.setContent(dto.content() == null ?article.getContent() : dto.content());
//        articleRepository.save(article);
            // updateArticle은 class level transactional에 의해 메서드 단위로 트랜잭션이 묶여 있어서
            // 트랜잭션이 끝날 때 영속성 컨텍스트가 article이 변경된 부분을 감지하여 쿼리를 날려준다.

        }catch (EntityNotFoundException e) {
            log.warn("게시글 업데이트 실패, 게시글을 찾을 수 없습니다. - dto: {}", dto);
        }
    }

    public void deleteArticle(long articleId) {
        articleRepository.deleteById(articleId);
    }
}
