package com.tapusd.springmybaties.service;

import com.tapusd.springmybaties.domain.Article;
import com.tapusd.springmybaties.dto.request.ArticleDTO;
import com.tapusd.springmybaties.exception.NotFoundException;
import com.tapusd.springmybaties.mapper.ArticleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class ArticleService {

    private final ArticleMapper articleMapper;

    @Autowired
    public ArticleService(ArticleMapper articleMapper) {
        this.articleMapper = articleMapper;
    }

    public List<Article> getArticles() {
        return articleMapper.getArticles();
    }

    public Article getArticle(Long id) {
        return articleMapper.getArticle(id);
    }

    public Article insert(ArticleDTO dto) {
        var article = new Article()
                .setTitle(dto.title())
                .setAuthor(dto.author());

        articleMapper.insert(article);
        return article;
    }

    public Article patch(Long id, ArticleDTO dto) {
        Assert.notNull(id, "Article id can not be null!");
        Assert.hasText(dto.title(), "Title can not be blank!");
        Assert.hasText(dto.author(), "Author can not be blank!");

        var article = new Article()
                .setId(id)
                .setTitle(dto.title())
                .setAuthor(dto.author());

        int updateCount = articleMapper.update(id, article);

        if (updateCount == 0) {
            throw new NotFoundException("Article with provided id not found!");
        }

        return article;
    }
}
