package com.edulearn.backend.controller;

import com.edulearn.backend.dto.UpdateProfileRequest;
import com.edulearn.backend.service.ProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {

    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping
    public ResponseEntity<?> getProfile(Authentication authentication) {
        try {
            return ResponseEntity.ok(profileService.getProfile(authentication.getName()));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<?> updateProfile(@RequestBody UpdateProfileRequest request, Authentication authentication) {
        try {
            return ResponseEntity.ok(profileService.updateProfile(authentication.getName(), request));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}