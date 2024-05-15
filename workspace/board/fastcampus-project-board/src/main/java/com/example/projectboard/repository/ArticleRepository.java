package com.example.projectboard.repository;

import com.example.projectboard.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource// 해당 repository를 기준으로 rest api를 사용할 수 있도록 해주는 어노테이션
public interface ArticleRepository extends JpaRepository<Article, Long> {

}