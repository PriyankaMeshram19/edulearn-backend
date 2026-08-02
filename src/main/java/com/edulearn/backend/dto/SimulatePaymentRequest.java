package com.edulearn.backend.dto;

import lombok.Data;

@Data
public class SimulatePaymentRequest {
    private Long courseId;
    private String paymentMethod;
}