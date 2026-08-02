package com.edulearn.backend.service.impl;

import com.edulearn.backend.dto.ProfileResponse;
import com.edulearn.backend.dto.UpdateProfileRequest;
import com.edulearn.backend.entity.User;
import com.edulearn.backend.repository.UserRepository;
import com.edulearn.backend.service.ProfileService;
import org.springframework.stereotype.Service;

@Service
public class ProfileServiceImpl implements ProfileService {

    private final UserRepository userRepository;

    public ProfileServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public ProfileResponse getProfile(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return new ProfileResponse(user.getName(), user.getEmail(), user.getRole().name());
    }

    @Override
    public ProfileResponse updateProfile(String email, UpdateProfileRequest request) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setName(request.getName());
        userRepository.save(user);

        return new ProfileResponse(user.getName(), user.getEmail(), user.getRole().name());
    }
}