package com.example.NewsService.specification;

import com.example.NewsService.dto.news.NewsFilter;
import com.example.NewsService.model.Category;
import com.example.NewsService.model.News;
import com.example.NewsService.model.User;
import io.micrometer.common.lang.Nullable;
import org.springframework.data.jpa.domain.Specification;

public interface NewsSpecification {

    static Specification<News> withFilter(NewsFilter filter) {
        return Specification.where(NewsSpecification.byUserId(filter.getUserId()))
                .and(NewsSpecification.byUserName(filter.getUserName()))
                .and(NewsSpecification.byCategoryId(filter.getCategoryId()))
                .and(NewsSpecification.byCategoryName(filter.getCategoryName()));
    }

    @Nullable
    static Specification<News> byUserId(Integer userId) {
        return (root, query, criteriaBuilder) -> {
            if (userId == null) {
                return null;
            }
            return criteriaBuilder.equal(root.get(News.Fields.user).get(User.Fields.id), userId);
        };
    }

    @Nullable
    static Specification<News> byUserName(String userName) {
        return (root, query, criteriaBuilder) -> {
            if (userName == null) {
                return null;
            }
            return criteriaBuilder.equal(root.get(News.Fields.user).get(User.Fields.name), userName);
        };
    }

    @Nullable
    static Specification<News> byCategoryId(Integer categoryId) {
        return (root, query, criteriaBuilder) -> {
            if (categoryId == null) {
                return null;
            }
            return criteriaBuilder.equal(root.get(News.Fields.category).get(Category.Fields.id), categoryId);
        };
    }

    @Nullable
    static Specification<News> byCategoryName(String categoryName) {
        return (root, query, criteriaBuilder) -> {
            if (categoryName == null) {
                return null;
            }
            return criteriaBuilder.equal(root.get(News.Fields.category).get(Category.Fields.name), categoryName);
        };
    }
}