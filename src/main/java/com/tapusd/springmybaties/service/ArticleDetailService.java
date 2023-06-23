package com.tapusd.springmybaties.service;

import com.tapusd.springmybaties.domain.Article;
import com.tapusd.springmybaties.domain.ArticleDetail;
import com.tapusd.springmybaties.domain.Author;
import com.tapusd.springmybaties.dto.request.ArticleDetailDTO;
import com.tapusd.springmybaties.mapper.ArticleDetailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ArticleDetailService {

    private final ArticleDetailMapper articleDetailMapper;
    private final AuthorService authorService;
    private final ArticleService articleService;

    @Autowired
    public ArticleDetailService(ArticleDetailMapper articleDetailMapper,
                                AuthorService authorService,
                                ArticleService articleService) {
        this.articleDetailMapper = articleDetailMapper;
        this.authorService = authorService;
        this.articleService = articleService;
    }

    public List<ArticleDetail> getArticleDetails() {
        return articleDetailMapper.getArticleDetails();
    }

    @Transactional
    public ArticleDetail insert(ArticleDetailDTO dto) {
        var author = new Author()
                .setName(dto.authorName())
                .setBio(dto.authorBio());

        authorService.insert(author);

        //// Test exception to test if transaction annotation work with mybatis. Uncomment to test!!!!
        //// Even though author will be inserted into database it will be rolled back because of the exception bellow
        // if (Objects.nonNull(author.getId())) {
        //    throw new IllegalStateException("author id is not null");
        //}

        var article = new Article()
                .setTitle(dto.articleTitle())
                .setAuthorId(author.getId());

        articleService.insert(article);

        return new ArticleDetail()
                .setId(article.getId())
                .setTitle(article.getTitle())
                .setAuthorName(author.getName());
    }
}
