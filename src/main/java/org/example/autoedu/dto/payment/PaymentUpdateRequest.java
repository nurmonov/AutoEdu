package org.example.autoedu.dto.payment;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data @Builder
public class PaymentUpdateRequest {
    private String fullName;
    private String paymentPhoto;
    private BigDecimal amount;
    private String paymentData;
    private String status;
}