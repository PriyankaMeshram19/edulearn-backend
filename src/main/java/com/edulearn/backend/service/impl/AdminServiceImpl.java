package com.edulearn.backend.service.impl;

import com.edulearn.backend.dto.AdminStatsResponse;
import com.edulearn.backend.entity.User;
import com.edulearn.backend.repository.CourseRepository;
import com.edulearn.backend.repository.EnrollmentRepository;
import com.edulearn.backend.repository.UserRepository;
import com.edulearn.backend.service.AdminService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    private final CourseRepository courseRepository;
    private final UserRepository userRepository;
    private final EnrollmentRepository enrollmentRepository;

    public AdminServiceImpl(CourseRepository courseRepository,
                            UserRepository userRepository,
                            EnrollmentRepository enrollmentRepository) {
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
        this.enrollmentRepository = enrollmentRepository;
    }

    @Override
    public AdminStatsResponse getStats() {
        long totalCourses = courseRepository.count();
        long totalStudents = userRepository.findByRole(User.Role.STUDENT).size();
        long totalEnrollments = enrollmentRepository.count();
        return new AdminStatsResponse(totalCourses, totalStudents, totalEnrollments);
    }

    @Override
    public List<User> getAllStudents() {
        return userRepository.findByRole(User.Role.STUDENT);
    }
}