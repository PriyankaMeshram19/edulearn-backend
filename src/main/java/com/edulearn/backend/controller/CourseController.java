package com.edulearn.backend.controller;

import com.edulearn.backend.dto.CourseRequest;
import com.edulearn.backend.entity.Course;
import com.edulearn.backend.service.CourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    // ---------- PUBLIC ENDPOINTS (Landing Page) ----------

    @GetMapping("/api/courses")
    public ResponseEntity<List<Course>> getPublishedCourses() {
        return ResponseEntity.ok(courseService.getPublishedCourses());
    }

    @GetMapping("/api/courses/{id}")
    public ResponseEntity<?> getCourseById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(courseService.getCourseById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // ---------- ADMIN-ONLY ENDPOINTS ----------

    @GetMapping("/api/admin/courses")
    public ResponseEntity<List<Course>> getAllCoursesForAdmin() {
        return ResponseEntity.ok(courseService.getAllCoursesForAdmin());
    }

    @PostMapping("/api/admin/courses")
    public ResponseEntity<?> addCourse(@RequestBody CourseRequest request) {
        try {
            return ResponseEntity.ok(courseService.addCourse(request));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/api/admin/courses/{id}")
    public ResponseEntity<?> updateCourse(@PathVariable Long id, @RequestBody CourseRequest request) {
        try {
            return ResponseEntity.ok(courseService.updateCourse(id, request));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/api/admin/courses/{id}")
    public ResponseEntity<?> deleteCourse(@PathVariable Long id) {
        try {
            courseService.deleteCourse(id);
            return ResponseEntity.ok("Course deleted successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
