package com.example.NewsService.specifications;

import com.example.NewsService.model.News;
import org.springframework.data.jpa.domain.Specification;

public class NewsSpecifications {

    public static Specification<News> hasCategory(Long categoryId) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("category").get("id"), categoryId);
    }

    public static Specification<News> hasAuthor(Long authorId) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("author").get("id"), authorId);
    }
}