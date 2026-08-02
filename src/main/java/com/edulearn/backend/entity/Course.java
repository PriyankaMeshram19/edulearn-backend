package com.edulearn.backend.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "courses")
@Data
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String authorName;

    @Column(length = 1000)
    private String description;

    private String thumbnailUrl;

    private BigDecimal price;

    private String youtubeVideoUrl;

    @Column(length = 2000)
    private String documentationContent;

    @Enumerated(EnumType.STRING)
    private Status status;

    private LocalDateTime createdAt = LocalDateTime.now();

    public enum Status { DRAFT, PUBLISHED }
}