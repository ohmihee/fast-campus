package com.example.projectboard.controller;

import com.example.projectboard.config.SecurityConfig;
import com.example.projectboard.domain.Article;
import com.example.projectboard.dto.ArticleDto;
import com.example.projectboard.dto.ArticleWithCommentDto;
import com.example.projectboard.dto.UserAccountDto;
import com.example.projectboard.repository.ArticleRepository;
import com.example.projectboard.service.ArticleService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@DisplayName("View 컨트롤러 - 게시글")
@Import(SecurityConfig.class)
@WebMvcTest(ArticleController.class)
// 인자로 준 클래스만 테스트 대상으로 처리하여 테스트 한다.
class ArticleControllerTest {


    private final MockMvc mvc;
    @MockBean private ArticleService articleService;
    @Autowired
    private ArticleRepository articleRepository;


    // 생성자가 하나만 존재할 때 테스트 파일이 아닌 경우에는  @Autowired를 생략하는 것이 가능하지만
    // 테스트 클래스인 경우에는 반드시 @Autowired를 추가해주어야 한다.
    ArticleControllerTest(@Autowired MockMvc mvc) {
        this.mvc = mvc;
    }

//    @Disabled("아직 구현 중")
    @DisplayName("[view][GET] 게시글 리스트 (게시판) 페이지 - 정상 호출")
    @Test
    public void givenNothing_whenRequestingArticlesView_thenReturnArticlesView() throws Exception {
        // Given
        given(articleService.searchArticles(eq(null), eq(null), any(Pageable.class))).willReturn(Page.empty());

        // When & Then
        mvc.perform(get("/articles"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("articles/index"))
                .andExpect(model().attributeExists("articles"))
                .andExpect(model().attributeExists("searchTypes"))
        ;
        then(articleService).should().searchArticles(eq(null), eq(null), any(Pageable.class));

    }

    @Disabled("아직 구현 중")
    @DisplayName("[view][GET]  게시글 상세 페이지 - 정상 호출")
    @Test
    public void givenNothing_whenRequestingArticleView_thenReturnArticleView() throws Exception {
        // Given
        Long articleId = 1L;
        given(articleService.searchArticle(articleId)).willReturn(createArticleWithCommentDto());

        // When & Then
        mvc.perform(get("/articles/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.TEXT_HTML))
                .andExpect(view().name("articles/detail"))
                .andExpect(model().attributeExists("article"))
                .andExpect(model().attributeExists("articleComments"))
        ;
    }

    @Disabled("아직 구현 중")
    @DisplayName("[view][GET]  게시글 검색 전용 페이지 - 정상 호출")
    @Test
    public void givenNothing_whenRequestingArticleSearchView_thenReturnArticleSearchView() throws Exception {
        // Given

        // When & Then
        mvc.perform(get("/articles/search"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.TEXT_HTML))
                .andExpect(model().attributeExists("articles/search"))
        ;
    }

    @Disabled("아직 구현 중")
    @DisplayName("[view][GET]  게시글 해시태그 검색 페이지 - 정상 호출")
    @Test
    public void givenNothing_whenRequestingArticleHashtagSearchView_thenReturnArticleHashtagSearchView() throws Exception {
        // Given

        // When & Then
        mvc.perform(get("/articles/search-hashtag"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.TEXT_HTML))
                .andExpect(model().attributeExists("articles/search-hashtag"))
        ;
    }

    private ArticleWithCommentDto createArticleWithCommentDto() {
        return ArticleWithCommentDto.of(
                1L,
                createUserAccoutDto(),
                Set.of(),
                "title",
                "content",
                "#java",
                LocalDateTime.now(),
                "mihee",
                LocalDateTime.now(),
                "mihee"
        );

    }

    private UserAccountDto createUserAccoutDto() {
        return UserAccountDto.of(1L, "mihhh", "mihhe");
    }



}