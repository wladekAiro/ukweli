package com.ukweli.news.repository;

import com.ukweli.news.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepo extends JpaRepository<Article, Long> {
    Article getBySlug(String slug);

}
