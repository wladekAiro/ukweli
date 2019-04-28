package com.ukweli.news.service.impl;

import com.ukweli.news.domain.Article;
import com.ukweli.news.domain.enumeration.ArticleStatus;
import com.ukweli.news.service.ArticleService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Override
    public Article create(Article article) {
        return null;
    }

    @Override
    public Article update(Article article) {
        return null;
    }

    @Override
    public Page<Article> getAllByStatus(ArticleStatus articleStatus, int page, int size) {
        return null;
    }

    @Override
    public Article updateStatus(String slug, Article status) {
        return null;
    }

    @Override
    public void delete(String slug) {

    }
}
