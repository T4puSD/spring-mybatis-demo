package com.tapusd.springmybaties.domain;

public class ArticleDetail {
    private Long id;
    private String title;
    private String authorName;

    public Long getId() {
        return id;
    }

    public ArticleDetail setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public ArticleDetail setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getAuthorName() {
        return authorName;
    }

    public ArticleDetail setAuthorName(String authorName) {
        this.authorName = authorName;
        return this;
    }
}
