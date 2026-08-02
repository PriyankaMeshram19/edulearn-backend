package com.edulearn.backend.service.impl;

import com.edulearn.backend.dto.CourseRequest;
import com.edulearn.backend.entity.Course;
import com.edulearn.backend.repository.CourseRepository;
import com.edulearn.backend.service.CourseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public List<Course> getPublishedCourses() {
        return courseRepository.findByStatus(Course.Status.PUBLISHED);
    }

    @Override
    public List<Course> getAllCoursesForAdmin() {
        return courseRepository.findAll();
    }

    @Override
    public Course getCourseById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));
    }

    @Override
    public Course addCourse(CourseRequest request) {
        Course course = new Course();
        mapRequestToCourse(request, course);
        return courseRepository.save(course);
    }

    @Override
    public Course updateCourse(Long id, CourseRequest request) {
        Course course = getCourseById(id);
        mapRequestToCourse(request, course);
        return courseRepository.save(course);
    }

    @Override
    public void deleteCourse(Long id) {
        Course course = getCourseById(id);
        courseRepository.delete(course);
    }

    private void mapRequestToCourse(CourseRequest request, Course course) {
        course.setTitle(request.getTitle());
        course.setAuthorName(request.getAuthorName());
        course.setDescription(request.getDescription());
        course.setThumbnailUrl(request.getThumbnailUrl());
        course.setPrice(request.getPrice());
        course.setYoutubeVideoUrl(request.getYoutubeVideoUrl());
        course.setDocumentationContent(request.getDocumentationContent());
        course.setStatus(Course.Status.valueOf(request.getStatus()));
    }
}