package com.example.NewsService.specification;

import com.example.NewsService.dto.comment.CommentFilter;
import com.example.NewsService.model.Comment;
import com.example.NewsService.model.User;
import io.micrometer.common.lang.Nullable;
import org.springframework.data.jpa.domain.Specification;

public interface CommentSpecification {
    static Specification<Comment> withFilter(CommentFilter filter) {
        return Specification.where(CommentSpecification.byUserId(filter.getUserId()));
    }

    @Nullable
    static Specification<Comment> byUserId(Integer userId) {
        return (root, query, criteriaBuilder) -> {
            if (userId == null) {
                return null;
            }
            return criteriaBuilder.equal(root.get(Comment.Fields.user).get(User.Fields.id), userId);
        };
    }
}