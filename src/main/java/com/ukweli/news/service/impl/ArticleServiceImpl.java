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
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
        page = page -1;
        return articlesRepo.getByStatus(articleStatus , new PageRequest(page,size));
    }

    @Override
    public Article updateStatus(String slug, ArticleStatus status) {

        Article articleInDb = articlesRepo.getBySlug(slug);

        if (!StringUtils.isEmpty(articleInDb)){

            if (articleInDb.getStatus().equals(ArticleStatus.REVIEW) && status.equals(ArticleStatus.PUBLISHED)){
                articleInDb.setEditor(userDetailsComponent.getLoggedInUser());
                articleInDb.setStatus(status);
            }

            if (!status.equals(ArticleStatus.PUBLISHED))
                articleInDb.setStatus(status);

            articlesRepo.save(articleInDb);
        }

        return articleInDb;
    }

    @Override
    public void delete(String slug) {
        Article articleInDb = articlesRepo.getBySlug(slug);
        articleInDb.setStatus(ArticleStatus.DELETED);
        articlesRepo.save(articleInDb);
    }
}
