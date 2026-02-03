package org.example.autoedu.dto.payment;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;


@Data @Builder
public class PaymentCreateRequest {
    @NotNull(message = "User ID majburiy")
    private Integer userId;
    private String fullName;
    private String paymentPhoto;     // chek rasmi URL
    private BigDecimal amount;
    private String paymentData;      // qo'shimcha ma'lumot
}