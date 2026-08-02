package com.edulearn.backend.service;

import com.edulearn.backend.dto.AdminStatsResponse;
import com.edulearn.backend.entity.User;
import java.util.List;

public interface AdminService {
    AdminStatsResponse getStats();
    List<User> getAllStudents();
}