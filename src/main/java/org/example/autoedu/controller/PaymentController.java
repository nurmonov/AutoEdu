//package org.example.autoedu.controller;
//
//
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.security.SecurityRequirement;
//import io.swagger.v3.oas.annotations.tags.Tag;
//import lombok.RequiredArgsConstructor;
//import org.example.autoedu.dto.payment.PaymentCreateRequest;
//import org.example.autoedu.dto.payment.PaymentResponse;
//import org.example.autoedu.dto.payment.PaymentUpdateRequest;
//import org.example.autoedu.service.PaymentService;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/payments")
//@RequiredArgsConstructor
//@Tag(name = "Payments", description = "To'lovlar bilan ishlash")
//@SecurityRequirement(name = "bearerAuth")
//public class PaymentController {
//
//    private final PaymentService paymentService;
//
//    // Foydalanuvchi o'z to'lovlarini ko'radi
//    @Operation(summary = "Foydalanuvchining to'lovlari")
//    @GetMapping("/my-payments")
//    public ResponseEntity<List<PaymentResponse>> getMyPayments(@AuthenticationPrincipal UserDetails userDetails) {
//        Integer userId = Integer.parseInt(userDetails.getUsername()); // phoneNumberdan userId olish kerak bo'lsa
//        return ResponseEntity.ok(paymentService.getPaymentsByUser(userId));
//    }
//
//    // Admin barcha to'lovlarni ko'radi
//    @Operation(summary = "Barcha to'lovlar (admin)")
//    @PreAuthorize("hasAnyRole('SCHOOL_ADMIN', 'SUPER_ADMIN')")
//    @GetMapping
//    public ResponseEntity<List<PaymentResponse>> getAllPayments() {
//        return ResponseEntity.ok(paymentService.getAllPayments());
//    }
//
//    // ID bo'yicha to'lov
//    @Operation(summary = "ID bo'yicha to'lov ma'lumotlari")
//    @GetMapping("/{id}")
//    public ResponseEntity<PaymentResponse> getPaymentById(@PathVariable Integer id) {
//        return ResponseEntity.ok(paymentService.getPaymentById(id));
//    }
//
//    // Yangi to'lov yaratish (foydalanuvchi chek yuborganida)
//    @Operation(summary = "Yangi to'lov yaratish")
//    @PostMapping
//    public ResponseEntity<PaymentResponse> createPayment(@RequestBody PaymentCreateRequest request) {
//        PaymentResponse response = paymentService.createPayment(request);
//        return new ResponseEntity<>(response, HttpStatus.CREATED);
//    }
//
//    // To'lov ma'lumotini yangilash (masalan chekni o'zgartirish)
//    @Operation(summary = "To'lov ma'lumotini yangilash")
//    @PutMapping("/{id}")
//    public ResponseEntity<PaymentResponse> updatePayment(
//            @PathVariable Integer id,
//            @RequestBody PaymentUpdateRequest request) {
//        return ResponseEntity.ok(paymentService.updatePayment(id, request));
//    }
//
//    // Admin tomonidan tasdiqlash
//    @Operation(summary = "To'lovni tasdiqlash (admin)")
//    @PreAuthorize("hasAnyRole('SCHOOL_ADMIN', 'SUPER_ADMIN')")
//    @PostMapping("/{id}/confirm")
//    public ResponseEntity<PaymentResponse> confirmPayment(@PathVariable Integer id) {
//        PaymentResponse response = paymentService.confirmPayment(id);
//        return ResponseEntity.ok(response);
//    }
//
//    // Admin tomonidan rad etish
//    @Operation(summary = "To'lovni rad etish (admin)")
//    @PreAuthorize("hasAnyRole('SCHOOL_ADMIN', 'SUPER_ADMIN')")
//    @PostMapping("/{id}/reject")
//    public ResponseEntity<PaymentResponse> rejectPayment(@PathVariable Integer id) {
//        PaymentResponse response = paymentService.rejectPayment(id);
//        return ResponseEntity.ok(response);
//    }
//}
