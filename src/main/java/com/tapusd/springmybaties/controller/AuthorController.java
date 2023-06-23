package com.tapusd.springmybaties.controller;

import com.tapusd.springmybaties.domain.Author;
import com.tapusd.springmybaties.dto.request.AuthorDTO;
import com.tapusd.springmybaties.dto.response.Response;
import com.tapusd.springmybaties.exception.NotFoundException;
import com.tapusd.springmybaties.service.AuthorService;
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
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public ResponseEntity<Response<List<Author>>> getAuthors() {
        var authors = authorService.getAuthors();
        return ResponseEntity.ok(Response.getSuccessDataResponse(authors));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<Author>> getAuthor(@PathVariable Long id) {
        var author = authorService.getAuthor(id)
                .orElseThrow(() -> new NotFoundException("Author not found with provided id!"));

        return ResponseEntity.ok(Response.getSuccessDataResponse(author));
    }

    @PostMapping
    public ResponseEntity<Response<Author>> saveAuthor(@RequestBody AuthorDTO dto) {
        var author = authorService.insert(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new Response<Author>()
                .setCode(HttpStatus.CREATED.value())
                .setMessage("Created")
                .setData(author)
        );
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Response<Author>> patchAuthor(@PathVariable Long id, @RequestBody AuthorDTO dto) {
        var author = authorService.patch(id, dto);
        return ResponseEntity.ok(Response.getSuccessDataResponse(author));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<Object>> deleteAuthor(@PathVariable Long id) {
        authorService.delete(id);
        return ResponseEntity.ok(Response.getSuccessResponse());
    }
}
