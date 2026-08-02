package com.edulearn.backend.service.impl;

import com.edulearn.backend.dto.SimulatePaymentRequest;
import com.edulearn.backend.entity.Course;
import com.edulearn.backend.entity.Payment;
import com.edulearn.backend.entity.User;
import com.edulearn.backend.repository.CourseRepository;
import com.edulearn.backend.repository.PaymentRepository;
import com.edulearn.backend.repository.UserRepository;
import com.edulearn.backend.service.EnrollmentService;
import com.edulearn.backend.service.PaymentService;
import com.edulearn.backend.util.EmailUtil;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final CourseRepository courseRepository;
    private final UserRepository userRepository;
    private final PaymentRepository paymentRepository;
    private final EnrollmentService enrollmentService;
    private final EmailUtil emailUtil;

    public PaymentServiceImpl(CourseRepository courseRepository,
                              UserRepository userRepository,
                              PaymentRepository paymentRepository,
                              EnrollmentService enrollmentService,
                              EmailUtil emailUtil) {
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
        this.paymentRepository = paymentRepository;
        this.enrollmentService = enrollmentService;
        this.emailUtil = emailUtil;
    }

    @Override
    public String simulatePayment(SimulatePaymentRequest request, String studentEmail) {
        User user = userRepository.findByEmail(studentEmail)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        Course course = courseRepository.findById(request.getCourseId())
                .orElseThrow(() -> new RuntimeException("Course not found"));

        String transactionId = "TXN" + UUID.randomUUID().toString()
                .replace("-", "").substring(0, 12).toUpperCase();

        Payment payment = new Payment();
        payment.setUser(user);
        payment.setCourse(course);
        payment.setPaymentMethod(request.getPaymentMethod());
        payment.setTransactionId(transactionId);
        payment.setAmount(course.getPrice());
        payment.setStatus(Payment.Status.SUCCESS);
        paymentRepository.save(payment);

        enrollmentService.enrollStudent(studentEmail, course.getId());

        emailUtil.sendEmail(
                user.getEmail(),
                "Payment Receipt - EduLearn",
                "Hi " + user.getName() + ",\n\n" +
                        "Your payment was successful!\n\n" +
                        "Course: " + course.getTitle() + "\n" +
                        "Amount: Rs. " + course.getPrice() + "\n" +
                        "Payment Method: " + request.getPaymentMethod() + "\n" +
                        "Transaction ID: " + transactionId + "\n\n" +
                        "You can now access this course from your Student Dashboard.\n\n" +
                        "— EduLearn Team"
        );

        return "Payment successful. You are now enrolled in " + course.getTitle() + "!";
    }
}