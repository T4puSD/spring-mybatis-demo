package com.tapusd.springmybaties.service;

import com.tapusd.springmybaties.domain.Author;
import com.tapusd.springmybaties.dto.request.AuthorDTO;
import com.tapusd.springmybaties.exception.DatabaseInsertException;
import com.tapusd.springmybaties.exception.NotFoundException;
import com.tapusd.springmybaties.mapper.AuthorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    private final AuthorMapper authorMapper;

    @Autowired
    public AuthorService(AuthorMapper authorMapper) {
        this.authorMapper = authorMapper;
    }

    public List<Author> getAuthors() {
        return authorMapper.getAuthors();
    }

    public Optional<Author> getAuthor(Long id) {
        Assert.notNull(id, "Id can not be null");
        return authorMapper.getAuthor(id);
    }

    public Author insert(AuthorDTO dto) {
        var author = new Author()
                .setName(dto.name())
                .setBio(dto.bio());

        int insertCount = authorMapper.insert(author);

        if (insertCount == 0) {
            throw new DatabaseInsertException("Unable to insert Author!");
        }
        return author;
    }

    public Author patch(Long id, AuthorDTO dto) {
        Assert.notNull(id, "Id can not be null");
        Assert.hasText(dto.name(), "Author name can not be blank!");

        var author = new Author()
                .setId(id)
                .setName(dto.name())
                .setBio(dto.bio());

        int updateCount = authorMapper.update(id, author);

        if (updateCount == 0) {
            throw new NotFoundException("Author with provided id not found!");
        }

        return author;
    }

    public void delete(Long id) {
        Assert.notNull(id, "Id can not be null!");

        int deleteCount = authorMapper.delete(id);

        if (deleteCount == 0) {
            throw new NotFoundException("Author with provided id not found!");
        }
    }
}
