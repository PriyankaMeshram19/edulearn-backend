package com.edulearn.backend.service;

import com.edulearn.backend.dto.*;

public interface AuthService {
    String register(RegisterRequest request);
    AuthResponse login(LoginRequest request);
    String forgotPassword(ForgotPasswordRequest request);
    String resetPassword(ResetPasswordRequest request);
}