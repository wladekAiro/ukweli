package com.ukweli.news.service;

import com.ukweli.news.domain.Article;
import com.ukweli.news.domain.enumeration.ArticleStatus;
import org.springframework.data.domain.Page;

public interface ArticleService {
    Article create(Article article);
    Article update(Article article);
    Page<Article> getAllByStatus(ArticleStatus articleStatus , int page, int size);
    Article updateStatus(String slug, ArticleStatus status);
    void delete(String slug);
}
