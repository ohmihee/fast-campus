package com.example.projectboard.service;

import com.example.projectboard.domain.Article;
import com.example.projectboard.domain.type.SearchType;
import com.example.projectboard.dto.ArticleDto;
import com.example.projectboard.dto.ArticleUdo;
import com.example.projectboard.repository.ArticleRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;

@DisplayName("비즈니스 로직-게시글")
@ExtendWith(MockitoExtension.class)
class ArticleServiceTest {
    @InjectMocks private ArticleService sut;
    @Mock private ArticleRepository articleRepository;

    @Test
    @DisplayName("게시글을 검색하면, 게시글 리스트를 반환한다.")
    void givenSearchParameters_whenSearchingArticles_thenReturnsArticleList() {
        // Given

        // When
        Page<ArticleDto> articles = sut.searchArticles(SearchType.TITLE, "search keyword"); // 제목, 본문, ID, 닉네임, 해시태그

        // Then
        assertThat(articles).isNotNull();
    }

    @Test
    @DisplayName("게시글을 조회하면, 게시글을 반환한다.")
    void givenArticleId_whenSearchingArticles_thenReturnsArticleList() {
        // Given

        // When
        ArticleDto article = sut.searchArticle(1L);

        assertThat(article).isNotNull();
    }


    @Test
    @DisplayName("게시글 정보를 입력하면, 게시글을 생성한다.")
    void givenArticleInfo_whenSavingArticle_thenSaveArticle(){
        // Give
        ArticleDto dto = ArticleDto.of(LocalDateTime.now(), ",ihee", "title", "content", "hashtage");

        given(articleRepository.save(any(Article.class))).willReturn(null);

        // When
        sut.saveArticle(dto);

        // Then
        then(articleRepository).should().save(any(Article.class));

    }

    @Test
    @DisplayName("게시글 ID와 수정 정보를 입력하면, 게시글을 수정한다.")
    void givenArticleIdAndModifiedInfo_whenUpdatingArticle_thenModifiesArticle(){
        // Give
        ArticleUdo udo = ArticleUdo.of("title", "content", "hashtage");

        given(articleRepository.save(any(Article.class))).willReturn(null);

        // When
        sut.updateArticle(1L, udo);

        // Then
        then(articleRepository).should().save(any(Article.class));

    }

    @Test
    @DisplayName("게시글 ID를 입력하면, 게시글을 삭제한다.")
    void givenArticleId_whenDeletingArticle_thenDeletesArticle(){
        // Give
        ArticleUdo udo = ArticleUdo.of("title", "content", "hashtage");

        willDoNothing().given(articleRepository).delete(any(Article.class));

        // When
        sut.deleteArticle(1L);

        // Then
        then(articleRepository).should().delete(any(Article.class));

    }
}