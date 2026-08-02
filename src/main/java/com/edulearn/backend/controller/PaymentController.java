package com.edulearn.backend.controller;

import com.edulearn.backend.dto.SimulatePaymentRequest;
import com.edulearn.backend.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/api/payment/simulate")
    public ResponseEntity<?> simulatePayment(@RequestBody SimulatePaymentRequest request,
                                             Authentication authentication) {
        try {
            String studentEmail = authentication.getName();
            return ResponseEntity.ok(paymentService.simulatePayment(request, studentEmail));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}