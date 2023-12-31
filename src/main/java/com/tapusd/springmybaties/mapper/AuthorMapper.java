package com.tapusd.springmybaties.mapper;

import com.tapusd.springmybaties.domain.Author;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Optional;

public interface AuthorMapper {

    @Select("SELECT * FROM author")
    List<Author> getAuthors();

    @Select("SELECT * FROM author WHERE id = #{id}")
    Optional<Author> getAuthor(@Param("id") Long id);

    @Insert("INSERT INTO author (name, bio) VALUES(#{name}, #{bio})")
    // will set the sequence value from database to Author object
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty= "id")
    int insert(Author author);

    @Update("UPDATE author SET name = #{author.name}, bio = #{author.bio} WHERE id = #{id}")
    int update(@Param("id") Long id, Author author);

    @Delete("DELETE FROM author WHERE id = #{id}")
    int delete(@Param("id") Long id);
}
