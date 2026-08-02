package com.edulearn.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class CourseEnrollmentResponse {
    private Long enrollmentId;
    private String studentName;
    private String studentEmail;
    private LocalDateTime enrolledAt;
    private boolean completed;
}