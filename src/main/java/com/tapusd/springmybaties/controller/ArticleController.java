package com.tapusd.springmybaties.controller;

import com.tapusd.springmybaties.domain.Article;
import com.tapusd.springmybaties.dto.request.ArticleDTO;
import com.tapusd.springmybaties.dto.response.Response;
import com.tapusd.springmybaties.exception.NotFoundException;
import com.tapusd.springmybaties.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/articles")
public class ArticleController {

    private final ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping
    public ResponseEntity<Response<List<Article>>> getAll() {
        var articles = articleService.getArticles();
        return ResponseEntity.ok(Response.getSuccessDataResponse(articles));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<Article>> getArticleById(@PathVariable Long id) {
        var article = articleService.getArticle(id)
                .orElseThrow(() -> new NotFoundException("Article not found with provided id"));
        return ResponseEntity.ok(Response.getSuccessDataResponse(article));
    }

    @PostMapping
    public ResponseEntity<Response<Article>> save(@RequestBody ArticleDTO dto) {
        var article = articleService.insert(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new Response<Article>()
                .setCode(HttpStatus.CREATED.value())
                .setMessage("Created")
                .setData(article)
        );
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Response<Article>> patchArticle(@PathVariable Long id, @RequestBody ArticleDTO dto) {
        var patchedArticle = articleService.patch(id, dto);
        return ResponseEntity.ok(Response.getSuccessDataResponse(patchedArticle));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<Object>> deleteArticle(@PathVariable Long id) {
        articleService.delete(id);
        return ResponseEntity.ok(Response.getSuccessResponse());
    }
}
