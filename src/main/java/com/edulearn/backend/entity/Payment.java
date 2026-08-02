package com.edulearn.backend.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
@Data
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    private String paymentMethod; // "CARD", "UPI", "NETBANKING", "QR_CODE"

    private String transactionId;

    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    private Status status;

    private LocalDateTime createdAt = LocalDateTime.now();

    public enum Status { SUCCESS, FAILED }
}