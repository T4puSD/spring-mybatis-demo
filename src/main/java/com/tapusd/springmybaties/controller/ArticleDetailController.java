package com.tapusd.springmybaties.controller;

import com.tapusd.springmybaties.domain.ArticleDetail;
import com.tapusd.springmybaties.dto.request.ArticleDetailDTO;
import com.tapusd.springmybaties.dto.response.Response;
import com.tapusd.springmybaties.service.ArticleDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/article-details")
public class ArticleDetailController {

   private final ArticleDetailService articleDetailService;

   @Autowired
    public ArticleDetailController(ArticleDetailService articleDetailService) {
        this.articleDetailService = articleDetailService;
    }

    @GetMapping
    public ResponseEntity<Response<List<ArticleDetail>>> getArticleDetails() {
        var articleDetails = articleDetailService.getArticleDetails();
        return ResponseEntity.ok(Response.getSuccessDataResponse(articleDetails));
    }

    @PostMapping
    public ResponseEntity<Response<ArticleDetail>> saveArticleDetail(@RequestBody ArticleDetailDTO dto) {
       var detail = articleDetailService.insert(dto);
       return ResponseEntity.status(HttpStatus.CREATED).body(new Response<ArticleDetail>()
               .setCode(HttpStatus.CREATED.value())
               .setMessage("Success")
               .setData(detail)
       );
    }
}
