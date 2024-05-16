package com.example.NewsService.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
@Getter
@Setter
public class CommentDTO {
    private Long id;

    @NotBlank
    private String content;

    @NotNull
    private Long newsId;

    @NotNull
    private Long authorId;
}
