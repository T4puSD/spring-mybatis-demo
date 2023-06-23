package com.tapusd.springmybaties.domain;

public class Article {
    private Long id;
    private String title;
    private Long authorId;

    public Long getId() {
        return id;
    }

    public Article setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Article setTitle(String title) {
        this.title = title;
        return this;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public Article setAuthorId(Long authorId) {
        this.authorId = authorId;
        return this;
    }
}
