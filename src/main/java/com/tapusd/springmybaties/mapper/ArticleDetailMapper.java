package com.tapusd.springmybaties.mapper;

import com.tapusd.springmybaties.domain.ArticleDetail;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ArticleDetailMapper {

    @Select("SELECT a.id, a.title, au.name AS authorName FROM article a JOIN author au ON a.author_id = au.id")
    public List<ArticleDetail> getArticleDetails();
}
