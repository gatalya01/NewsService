package com.example.NewsService.mapper;

import com.example.NewsService.aop.Loggable;
import com.example.NewsService.dto.comment.CommentListResponse;
import com.example.NewsService.dto.comment.CommentResponse;
import com.example.NewsService.dto.comment.CommentUpsertRequest;
import com.example.NewsService.model.Comment;
import org.mapstruct.*;

import java.util.List;

@DecoratedWith(CommentMapperDelegate.class)
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CommentMapper {
    Comment requestToComment(CommentUpsertRequest request);

    CommentResponse commentToCommentResponse(Comment comment);

    List<CommentResponse> commentListToListOfCommentResponse(List<Comment> comments);

    @Loggable
    default CommentListResponse commentListToCommentListResponse(List<Comment> comments) {
        return new CommentListResponse(this.commentListToListOfCommentResponse(comments));
    }
}