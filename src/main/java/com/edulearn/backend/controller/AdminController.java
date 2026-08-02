package com.edulearn.backend.controller;

import com.edulearn.backend.service.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/api/admin/stats")
    public ResponseEntity<?> getStats() {
        return ResponseEntity.ok(adminService.getStats());
    }

    @GetMapping("/api/admin/students")
    public ResponseEntity<?> getAllStudents() {
        return ResponseEntity.ok(adminService.getAllStudents());
    }
}