package com.edulearn.backend.repository;

import com.edulearn.backend.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByStatus(Course.Status status);
}