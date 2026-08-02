package com.edulearn.backend.service;

import com.edulearn.backend.dto.SimulatePaymentRequest;

public interface PaymentService {
    String simulatePayment(SimulatePaymentRequest request, String studentEmail);
}