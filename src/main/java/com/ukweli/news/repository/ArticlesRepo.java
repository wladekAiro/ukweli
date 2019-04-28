package com.ukweli.news.repository;

import com.ukweli.news.domain.Article;
import com.ukweli.news.domain.User;
import com.ukweli.news.domain.enumeration.ArticleStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticlesRepo extends JpaRepository<Article, Long> {
    Article getBySlug(String slug);
    Page<Article> getByStatus(ArticleStatus articleStatus , Pageable pageable);
    Page<Article> getByStatusAndWriter(ArticleStatus articleStatus, User writer, Pageable pageable);
}
