package com.tapusd.springmybaties.dto.request;

public record ArticleDetailDTO(Long articleId, String articleTitle, Long authorId, String authorName, String authorBio) {
}
