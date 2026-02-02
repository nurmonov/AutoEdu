//package org.example.autoedu.service;
//
//import lombok.RequiredArgsConstructor;
//import org.example.autoedu.dto.payment.PaymentCreateRequest;
//import org.example.autoedu.dto.payment.PaymentResponse;
//import org.example.autoedu.dto.payment.PaymentUpdateRequest;
//import org.example.autoedu.entity.Payment;
//import org.example.autoedu.entity.enums.PaymentStatus;
//import org.example.autoedu.repo.PaymentRepository;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//import java.util.NoSuchElementException;
//
//@Service
//@RequiredArgsConstructor
//public class PaymentService {
//
//    private final PaymentRepository paymentRepository;
//
//    @Transactional(readOnly = true)
//    public List<PaymentResponse> getAllPayments() {
//        return paymentRepository.findAll().stream()
//                .map(this::toResponse)
//                .toList();
//    }
//
//    @Transactional(readOnly = true)
//    public PaymentResponse getPaymentById(Integer id) {
//        Payment payment = paymentRepository.findById(id)
//                .orElseThrow(() -> new NoSuchElementException("To'lov topilmadi: " + id));
//        return toResponse(payment);
//    }
//
//    @Transactional(readOnly = true)
//    public List<PaymentResponse> getPaymentsByUser(Integer userId) {
//        return paymentRepository.findByUserId(userId).stream()
//                .map(this::toResponse)
//                .toList();
//    }
//
//    @Transactional
//    public PaymentResponse createPayment(PaymentCreateRequest request) {
//        Payment payment = Payment.builder()
//                .userId(request.getUserId())
//                .fullName(request.getFullName())
//                .paymentPhoto(request.getPaymentPhoto())
//                .amount(request.getAmount())
//                .paymentData(request.getPaymentData())
//                .status(PaymentStatus.PENDING)  // yangi to'lov har doim PENDING
//                .build();
//
//        Payment saved = paymentRepository.save(payment);
//        return toResponse(saved);
//    }
//
//    @Transactional
//    public PaymentResponse updatePayment(Integer id, PaymentUpdateRequest request) {
//        Payment payment = paymentRepository.findById(id)
//                .orElseThrow(() -> new NoSuchElementException("To'lov topilmadi: " + id));
//
//        // Qo'lda yangilash (null bo'lmagan maydonlar o'zgartiriladi)
//        if (request.getFullName() != null) payment.setFullName(request.getFullName());
//        if (request.getPaymentPhoto() != null) payment.setPaymentPhoto(request.getPaymentPhoto());
//        if (request.getAmount() != null) payment.setAmount(request.getAmount());
//        if (request.getPaymentData() != null) payment.setPaymentData(request.getPaymentData());
//        if (request.getStatus() != null) payment.setStatus(PaymentStatus.valueOf(request.getStatus()));
//
//        Payment updated = paymentRepository.save(payment);
//        return toResponse(updated);
//    }
//
//    @Transactional
//    public PaymentResponse confirmPayment(Integer id) {
//        Payment payment = paymentRepository.findById(id)
//                .orElseThrow(() -> new NoSuchElementException("To'lov topilmadi: " + id));
//
//        if (payment.getStatus() != PaymentStatus.PENDING) {
//            throw new IllegalStateException("Bu to'lov allaqachon tasdiqlangan yoki rad etilgan");
//        }
//
//        payment.setStatus(PaymentStatus.CANCELLED);
//        Payment updated = paymentRepository.save(payment);
//        return toResponse(updated);
//    }
//
//    @Transactional
//    public PaymentResponse rejectPayment(Integer id) {
//        Payment payment = paymentRepository.findById(id)
//                .orElseThrow(() -> new NoSuchElementException("To'lov topilmadi: " + id));
//
//        if (payment.getStatus() != PaymentStatus.PENDING) {
//            throw new IllegalStateException("Bu to'lov allaqachon tasdiqlangan yoki rad etilgan");
//        }
//
//        payment.setStatus(PaymentStatus.SUCCESS);
//        Payment updated = paymentRepository.save(payment);
//        return toResponse(updated);
//    }
//
//    private PaymentResponse toResponse(Payment entity) {
//        return PaymentResponse.builder()
//                .id(entity.getId())
//                .userId(entity.getUserId())
//                .fullName(entity.getFullName())
//                .paymentPhoto(entity.getPaymentPhoto())
//                .amount(entity.getAmount())
//                .paymentData(entity.getPaymentData())
//                .status(String.valueOf(entity.getStatus()))
//                .build();
//    }
//}