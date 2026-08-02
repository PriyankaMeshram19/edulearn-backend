package com.edulearn.backend.service;

import com.edulearn.backend.dto.CourseEnrollmentResponse;
import com.edulearn.backend.entity.Enrollment;
import java.util.List;

public interface EnrollmentService {
    Enrollment enrollStudent(String studentEmail, Long courseId);
    List<Enrollment> getStudentEnrollments(String studentEmail);
    Enrollment markAsCompleted(Long enrollmentId, String studentEmail);
    long getEnrollmentCountForCourse(Long courseId);
    List<CourseEnrollmentResponse> getEnrollmentsForCourse(Long courseId);
    Enrollment getEnrollmentById(Long enrollmentId, String studentEmail);
}