package com.tapusd.springmybaties.domain;

public class Author {
    private Long id;
    private String name;
    private String bio;

    public Long getId() {
        return id;
    }

    public Author setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Author setName(String name) {
        this.name = name;
        return this;
    }

    public String getBio() {
        return bio;
    }

    public Author setBio(String bio) {
        this.bio = bio;
        return this;
    }
}
