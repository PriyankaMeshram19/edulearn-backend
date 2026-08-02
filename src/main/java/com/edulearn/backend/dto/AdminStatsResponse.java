package com.edulearn.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AdminStatsResponse {
    private long totalCourses;
    private long totalStudents;
    private long totalEnrollments;
}