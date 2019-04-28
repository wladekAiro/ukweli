package com.ukweli.news.service.impl;

import com.ukweli.news.components.UserDetailsComponent;
import com.ukweli.news.domain.Article;
import com.ukweli.news.domain.enumeration.ArticleStatus;
import com.ukweli.news.repository.ArticlesRepo;
import com.ukweli.news.service.ArticleService;
import com.ukweli.news.service.ImageUploadService;
import com.ukweli.news.utils.SlugUtil;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    ImageUploadService imageUploadService;
    @Autowired
    UserDetailsComponent userDetailsComponent;
    @Autowired
    ArticlesRepo articlesRepo;

    @Override
    public Article create(Article article) {
        String slug = DateTime.now().millisOfDay() + "-" + SlugUtil.toSlug(article.getTitle());
        String imageUrl = imageUploadService.uploadImage(article.getImage());

        article.setMainImageUrl(imageUrl);
        article.setSlug(slug);
        article.setWriter(userDetailsComponent.getLoggedInUser());

        return articlesRepo.save(article);
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
