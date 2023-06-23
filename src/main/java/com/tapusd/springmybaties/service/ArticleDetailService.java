package com.tapusd.springmybaties.service;

import com.tapusd.springmybaties.domain.ArticleDetail;
import com.tapusd.springmybaties.mapper.ArticleDetailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleDetailService {

    private final ArticleDetailMapper articleDetailMapper;

    @Autowired
    public ArticleDetailService(ArticleDetailMapper articleDetailMapper) {
        this.articleDetailMapper = articleDetailMapper;
    }

    public List<ArticleDetail> getArticleDetails() {
        return articleDetailMapper.getArticleDetails();
    }
}
