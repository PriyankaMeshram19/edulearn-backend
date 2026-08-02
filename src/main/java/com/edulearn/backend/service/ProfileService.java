package com.edulearn.backend.service;

import com.edulearn.backend.dto.ProfileResponse;
import com.edulearn.backend.dto.UpdateProfileRequest;

public interface ProfileService {
    ProfileResponse getProfile(String email);
    ProfileResponse updateProfile(String email, UpdateProfileRequest request);
}