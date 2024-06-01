package com.example.NewsService.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;


@Data
@NoArgsConstructor
@FieldNameConstants
@Entity(name = "comments")
public class Comment implements Identifiable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String content;
    @ManyToOne
    @JoinColumn(name = "news_id")
    @ToString.Exclude
    private News news;
    @ManyToOne
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    private User user;
    @CreationTimestamp
    private Instant createdAt;
    @UpdateTimestamp
    private Instant updatedAt;

    @JsonCreator
    public Comment(@JsonProperty("content") String content) {
        this.content = content;
    }

    public Comment(String content, User user) {
        this.content = content;
        this.user = user;
    }

    public Comment(String content, News news, User user) {
        this.content = content;
        this.news = news;
        this.user = user;
    }

    public Comment(int id, String content, News news, User user) {
        this.id = id;
        this.content = content;
        this.news = news;
        this.user = user;
    }
}