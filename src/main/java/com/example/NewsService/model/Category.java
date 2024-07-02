package com.example.NewsService.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants
@Entity(name = "categories")
public class Category implements Identifiable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<News> news = new ArrayList<>();

    @CreationTimestamp
    @Column(name = "created")
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "updated")
    private Instant updatedAt;

    public Category(String name) {
        this.name = name;
    }
}