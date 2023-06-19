package com.tapusd.springmybaties.mapper;

import com.tapusd.springmybaties.domain.Article;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface ArticleMapper {

    @Select("SELECT * FROM article")
    List<Article> getArticles();

    @Select("SELECT * FROM article where id = #{id}")
    Article getArticle(@Param("id") Long id);

    @Insert(value = "INSERT INTO article (title, author) VALUES (#{title}, #{author})")
    // this will set the auto generated primary key from the database to the Article object
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void insert(Article article);

    @Update("UPDATE article SET title = #{article.title}, author = #{article.author} WHERE id = #{id}")
    int update(@Param("id") Long id, Article article);
}
