package com.tapusd.springmybaties.service;

import com.tapusd.springmybaties.domain.Article;
import com.tapusd.springmybaties.dto.request.ArticleDTO;
import com.tapusd.springmybaties.exception.DatabaseInsertException;
import com.tapusd.springmybaties.exception.NotFoundException;
import com.tapusd.springmybaties.mapper.ArticleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

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

    public Optional<Article> getArticle(Long id) {
        return articleMapper.getArticle(id);
    }

    public Article insert(ArticleDTO dto) {
        var article = new Article()
                .setTitle(dto.title())
                .setAuthorId(dto.authorId());

        insert(article);
        return article;
    }

    public void insert(Article article) {
        Assert.hasText(article.getTitle(), "Title can not be blank!");
        Assert.notNull(article.getAuthorId(), "Author id can not be null!");

        int insertCount = articleMapper.insert(article);

        if (insertCount == 0) {
            throw new DatabaseInsertException("Unable to insert article!");
        }

        if (Objects.isNull(article.getId())) {
            throw new IllegalStateException("Mybatis failed to initialize article id!");
        }
    }

    public Article patch(Long id, ArticleDTO dto) {
        Assert.notNull(id, "Article id can not be null!");
        Assert.hasText(dto.title(), "Title can not be blank!");
        Assert.notNull(dto.authorId(), "Author id can not be null!");

        var article = new Article()
                .setId(id)
                .setTitle(dto.title())
                .setAuthorId(dto.authorId());

        int updateCount = articleMapper.update(id, article);

        if (updateCount == 0) {
            throw new NotFoundException("Article with provided id not found!");
        }

        return article;
    }

    public void delete(Long id) {
        int deleteCount = articleMapper.delete(id);

        if (deleteCount == 0) {
            throw new NotFoundException("Article with provided id not found!");
        }
    }
}
