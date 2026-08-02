package com.edulearn.backend.service;

import com.edulearn.backend.dto.CourseRequest;
import com.edulearn.backend.entity.Course;
import java.util.List;

public interface CourseService {
    List<Course> getPublishedCourses();      // for Landing Page (public)
    List<Course> getAllCoursesForAdmin();    // for Admin Dashboard (all, incl. drafts)
    Course getCourseById(Long id);
    Course addCourse(CourseRequest request);
    Course updateCourse(Long id, CourseRequest request);
    void deleteCourse(Long id);
}
