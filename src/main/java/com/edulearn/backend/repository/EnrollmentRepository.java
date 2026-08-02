package com.edulearn.backend.repository;

import com.edulearn.backend.entity.Course;
import com.edulearn.backend.entity.Enrollment;
import com.edulearn.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    List<Enrollment> findByStudent(User student);
    Optional<Enrollment> findByStudentAndCourse(User student, Course course);
    List<Enrollment> findByCourse(Course course);
    long countByCourse(Course course);
    long countByStudent(User student);

}