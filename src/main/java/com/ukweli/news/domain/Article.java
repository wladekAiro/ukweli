package com.ukweli.news.domain;

import com.ukweli.news.domain.enumeration.ArticleStatus;

import javax.persistence.*;

@Entity
@Table(name = "articles")
public class Article extends AbstractModel {
    @Column(nullable = false)
    private String title;
    @Column(unique = true , nullable = false)
    private String slug;
    @Column(nullable = false)
    private String mainImageUrl;
    @Column(columnDefinition = "TEXT" , nullable = false)
    private String body;
    @ManyToOne
    private User writer;
    @Enumerated(value = EnumType.STRING)
    private ArticleStatus status = ArticleStatus.PENDING;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public User getWriter() {
        return writer;
    }

    public void setWriter(User writer) {
        this.writer = writer;
    }

    public String getMainImageUrl() {
        return mainImageUrl;
    }

    public void setMainImageUrl(String mainImageUrl) {
        this.mainImageUrl = mainImageUrl;
    }

    public ArticleStatus getStatus() {
        return status;
    }

    public void setStatus(ArticleStatus status) {
        this.status = status;
    }
}
