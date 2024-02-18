package com.example.projectboard.repository;

import com.example.projectboard.config.JpaConfig;
import com.example.projectboard.domain.Article;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
/**
 * application.yml에서 해당 프로파일로 설정한 데이터베이스를 사용하도록 설정
 * @ActiveProfiles("testdb")
 * @AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) 또는 해당 부분을 application.yml으로 설정해줄 수 도 있다.
 * */
//@ActiveProfiles("testdb")
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DisplayName("JPA 연결 테스트")
/**
 * 테스트 코드의 경우 config 파일 설정이 제대로 되지 않는 경우가 있다.
 * 그런 경우 해당 설정 파일을 아래와 같이 불러와 주면 된다.
 * */
@Import(JpaConfig.class)
@DataJpaTest
class JpaRepositoryTest {
    private final ArticleRepository articleRepository;
    private final ArticleCommentRepository articleCommentRepository;

    JpaRepositoryTest(
            @Autowired ArticleRepository articleRepository,
            @Autowired ArticleCommentRepository articleCommentRepository) {
        this.articleRepository = articleRepository;
        this.articleCommentRepository = articleCommentRepository;
    }

    @DisplayName("select 테스트")
    @Test
    void givenTestData_whenSelecting_thenWorksFind() {
        // Given

        // When
        List<Article> articles = articleRepository.findAll();

        // Then
        assertThat(articles).
                isNotNull()
                .hasSize(1);
    }

    @DisplayName("insert 테스트")
    @Test
    void givenTestData_whenInserting_thenWorksFine() {
        // given
        long previousCount = articleRepository.count();

        // when
        Article savedArticle = articleRepository.save(Article.of("title","content","hashtag"));

        // then
        assertThat(articleRepository.count()).isEqualTo(previousCount +1);
    }

    @DisplayName("update 테스트")
    @Test
    void givenTestData_whenUpdating_thenWorksFine() {
        // given
        Article article = articleRepository.findById(1L).orElseThrow();
        String updatedHashtag = "#springboot";
        article.setHashtag(updatedHashtag);

        // when
        Article savedArticle = articleRepository.saveAndFlush(article);
        // then
        assertThat(savedArticle).hasFieldOrPropertyWithValue("hashtag", updatedHashtag);
    }

    @DisplayName("delete 테스트")
    @Test
    void givenTestData_whenDeleting_thenWorksFine() {
        // given
        Article article = articleRepository.findById(1L).orElseThrow();
        long previousArticleCount = articleRepository.count();
        long previousArticleComment = articleCommentRepository.count();
        long deletedCommentsSize = article.getArticleComments().size();

        // when
        articleRepository.delete(article);

        // then
        assertThat(articleRepository.count()).isEqualTo(previousArticleCount -1);
        assertThat(articleCommentRepository.count()).isEqualTo(previousArticleComment - deletedCommentsSize);
    }


}