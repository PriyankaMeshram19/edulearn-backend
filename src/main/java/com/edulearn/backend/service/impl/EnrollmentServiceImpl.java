package com.edulearn.backend.service.impl;

import com.edulearn.backend.dto.CourseEnrollmentResponse;
import com.edulearn.backend.entity.Course;
import com.edulearn.backend.entity.Enrollment;
import com.edulearn.backend.entity.User;
import com.edulearn.backend.repository.CourseRepository;
import com.edulearn.backend.repository.EnrollmentRepository;
import com.edulearn.backend.repository.UserRepository;
import com.edulearn.backend.service.EnrollmentService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;

    public EnrollmentServiceImpl(EnrollmentRepository enrollmentRepository,
                                 UserRepository userRepository,
                                 CourseRepository courseRepository) {
        this.enrollmentRepository = enrollmentRepository;
        this.userRepository = userRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public Enrollment enrollStudent(String studentEmail, Long courseId) {
        User student = userRepository.findByEmail(studentEmail)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        enrollmentRepository.findByStudentAndCourse(student, course)
                .ifPresent(e -> { throw new RuntimeException("Already enrolled in this course"); });

        Enrollment enrollment = new Enrollment();
        enrollment.setStudent(student);
        enrollment.setCourse(course);
        enrollment.setCompleted(false);

        return enrollmentRepository.save(enrollment);
    }

    @Override
    public List<Enrollment> getStudentEnrollments(String studentEmail) {
        User student = userRepository.findByEmail(studentEmail)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        return enrollmentRepository.findByStudent(student);
    }

    @Override
    public Enrollment markAsCompleted(Long enrollmentId, String studentEmail) {
        Enrollment enrollment = enrollmentRepository.findById(enrollmentId)
                .orElseThrow(() -> new RuntimeException("Enrollment not found"));

        if (!enrollment.getStudent().getEmail().equals(studentEmail)) {
            throw new RuntimeException("Not authorized to update this enrollment");
        }

        enrollment.setCompleted(true);
        enrollment.setCompletedAt(LocalDateTime.now());
        return enrollmentRepository.save(enrollment);
    }

    @Override
    public long getEnrollmentCountForCourse(Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));
        return enrollmentRepository.countByCourse(course);
    }

    @Override
    public List<CourseEnrollmentResponse> getEnrollmentsForCourse(Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        return enrollmentRepository.findByCourse(course).stream()
                .map(e -> new CourseEnrollmentResponse(
                        e.getId(),
                        e.getStudent().getName(),
                        e.getStudent().getEmail(),
                        e.getEnrolledAt(),
                        e.isCompleted()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public Enrollment getEnrollmentById(Long enrollmentId, String studentEmail) {
        Enrollment enrollment = enrollmentRepository.findById(enrollmentId)
                .orElseThrow(() -> new RuntimeException("Enrollment not found"));

        if (!enrollment.getStudent().getEmail().equals(studentEmail)) {
            throw new RuntimeException("Not authorized to view this course");
        }

        return enrollment;
    }
}