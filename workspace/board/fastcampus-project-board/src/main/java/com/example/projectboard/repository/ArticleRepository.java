package com.example.projectboard.repository;

import com.example.projectboard.domain.Article;
import com.example.projectboard.domain.QArticle;
import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.core.types.dsl.SimpleExpression;
import com.querydsl.core.types.dsl.StringExpression;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource// 해당 repository를 기준으로 rest api를 사용할 수 있도록 해주는 어노테이션
public interface ArticleRepository extends JpaRepository<Article, Long>,
        QuerydslPredicateExecutor<Article>,
        // QuerydslPredicateExecutor는 Article 엔티티에 존재하는 모든 필드에 대해 검색 기능을 추가하여 준다.
        QuerydslBinderCustomizer<QArticle>
        // 검색 조건을 커스텀할 수 있도록 해준다.
{
    @Override
    default void customize(QuerydslBindings bindings, QArticle root) {
        // 해당 메서드에 구현된 로직을 바탕으로 하여 검색에 대한 세부적인 규칙이 재설정된다.
        bindings.excludeUnlistedProperties(true);
        //  QuerydslPredicateExecutor<Article> 로 인해 Article 클래스에 존재하는 모든 필드에 대한 검색이 가능해졌는데
        //  검색에서 특정 필드만 검색 가능하도록 설정해주는 것이다.
        bindings.including(root.title, root.content, root.hashtag, root.createdAt, root.createdBy);
//        bindings.bind(root.title).first(SimpleExpression::eq);
        bindings.bind(root.title).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.content).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.hashtag).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.createdAt).first(DateTimeExpression::eq);
        bindings.bind(root.createdBy).first(StringExpression::containsIgnoreCase);
        // title의 경우 하나의 파라미터만 받는다.
    }
    // 본래 인터페이스에 구현체를 넣는 것은 불가능했지 java8 이후로 가능해짐
    // 인터페이스에 구현하는 메서드이므로 default method로 구현


}