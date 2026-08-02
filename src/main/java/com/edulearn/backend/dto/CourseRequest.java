package com.edulearn.backend.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class CourseRequest {
    private String title;
    private String authorName;
    private String description;
    private String thumbnailUrl;
    private BigDecimal price;
    private String youtubeVideoUrl;
    private String documentationContent;
    private String status; // "DRAFT" or "PUBLISHED"
}