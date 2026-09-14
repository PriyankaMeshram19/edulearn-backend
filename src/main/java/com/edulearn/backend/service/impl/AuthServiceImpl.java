package com.edulearn.backend.service.impl;

import com.edulearn.backend.dto.*;
import com.edulearn.backend.entity.PasswordResetToken;
import com.edulearn.backend.entity.User;
import com.edulearn.backend.repository.PasswordResetTokenRepository;
import com.edulearn.backend.repository.UserRepository;
import com.edulearn.backend.security.JwtUtil;
import com.edulearn.backend.service.AuthService;
import com.edulearn.backend.util.EmailUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordResetTokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final EmailUtil emailUtil;
    @Value("${app.frontend-url}")
    private String frontendUrl;

    public AuthServiceImpl(UserRepository userRepository,
                           PasswordResetTokenRepository tokenRepository,
                           PasswordEncoder passwordEncoder,
                           JwtUtil jwtUtil,
                           EmailUtil emailUtil) {
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.emailUtil = emailUtil;
    }

    @Override
    public String register(RegisterRequest request) {
        request.setEmail(request.getEmail().toLowerCase().trim());

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already registered");
        }

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(User.Role.STUDENT);

        userRepository.save(user);

        try {
            emailUtil.sendEmail(
                    user.getEmail(),
                    "Welcome to EduLearn!",
                    "Hi " + user.getName() + ",\n\nYour account has been created successfully. Happy learning!\n\n— EduLearn Team"
            );
        } catch (Exception e) {
            System.out.println("Email sending failed but registration succeeded: " + e.getMessage());
        }

        return "Registered successfully";
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        request.setEmail(request.getEmail().toLowerCase().trim());
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }

        String token = jwtUtil.generateToken(user.getEmail(), user.getRole().name());

        return new AuthResponse(token, user.getRole().name(), user.getName());
    }


    @Override
    public String forgotPassword(ForgotPasswordRequest request) {
        request.setEmail(request.getEmail().toLowerCase().trim());

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("No account found with this email"));

        // Agar is user ka purana token hai, toh pehle usse delete karo
        tokenRepository.findByUser(user).ifPresent(tokenRepository::delete);

        String token = UUID.randomUUID().toString();

        PasswordResetToken resetToken = new PasswordResetToken();
        resetToken.setToken(token);
        resetToken.setUser(user);
        resetToken.setExpiryDate(LocalDateTime.now().plusMinutes(30));
        tokenRepository.save(resetToken);

        String resetLink = frontendUrl + "/reset-password?token=" + token;

        try {
            emailUtil.sendEmail(
                    user.getEmail(),
                    "Reset your EduLearn password",
                    "Click the link below to reset your password (valid for 30 minutes):\n\n" + resetLink
            );
        } catch (Exception e) {
            System.out.println("Email sending failed but reset token created: " + e.getMessage());
        }

        return "Password reset link sent to your email";
    }

    @Override
    public String resetPassword(ResetPasswordRequest request) {
        PasswordResetToken resetToken = tokenRepository.findByToken(request.getToken())
                .orElseThrow(() -> new RuntimeException("Invalid or expired reset link"));

        if (resetToken.getExpiryDate().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Reset link has expired");
        }

        User user = resetToken.getUser();
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);

        tokenRepository.delete(resetToken); // one-time use only

        return "Password reset successfully";
    }
}