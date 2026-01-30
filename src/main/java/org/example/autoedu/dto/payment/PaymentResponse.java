package org.example.autoedu.dto.payment;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Data @Builder
public class PaymentResponse {
    private Integer id;
    private Integer userId;
    private String fullName;
    private String paymentPhoto;
    private BigDecimal amount;
    private String paymentData;
    private String status;           // yoki PaymentStatus enum
}
