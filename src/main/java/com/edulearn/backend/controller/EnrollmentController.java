package com.edulearn.backend.controller;

import com.edulearn.backend.dto.EnrollmentRequest;
import com.edulearn.backend.entity.Enrollment;
import com.edulearn.backend.service.EnrollmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    // Student "Buy Now" click karta hai -> ye endpoint call hota hai
    @PostMapping("/api/enrollments")
    public ResponseEntity<?> enroll(@RequestBody EnrollmentRequest request, Authentication authentication) {
        try {
            String studentEmail = authentication.getName(); // JWT se email nikalta hai
            return ResponseEntity.ok(enrollmentService.enrollStudent(studentEmail, request.getCourseId()));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Student Dashboard - "My Courses" list
    @GetMapping("/api/enrollments/student")
    public ResponseEntity<List<Enrollment>> getMyEnrollments(Authentication authentication) {
        String studentEmail = authentication.getName();
        return ResponseEntity.ok(enrollmentService.getStudentEnrollments(studentEmail));
    }

    // "Mark as Complete" button
    @PatchMapping("/api/enrollments/{id}/complete")
    public ResponseEntity<?> markComplete(@PathVariable Long id, Authentication authentication) {
        try {
            String studentEmail = authentication.getName();
            return ResponseEntity.ok(enrollmentService.markAsCompleted(id, studentEmail));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Admin - kitne students is course mein enrolled hain
    @GetMapping("/api/admin/courses/{courseId}/enrollment-count")
    public ResponseEntity<Long> getEnrollmentCount(@PathVariable Long courseId) {
        return ResponseEntity.ok(enrollmentService.getEnrollmentCountForCourse(courseId));
    }

    @GetMapping("/api/admin/courses/{courseId}/enrollments")
    public ResponseEntity<?> getCourseEnrollments(@PathVariable Long courseId) {
        try {
            return ResponseEntity.ok(enrollmentService.getEnrollmentsForCourse(courseId));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/api/enrollments/{id}")
    public ResponseEntity<?> getEnrollmentById(@PathVariable Long id, Authentication authentication) {
        try {
            String studentEmail = authentication.getName();
            return ResponseEntity.ok(enrollmentService.getEnrollmentById(id, studentEmail));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}