package org.example.autoedu.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.autoedu.entity.enums.PaymentStatus;

import java.math.BigDecimal;


@Entity
@Table(name = "payment")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "payment_photo")
    private String paymentPhoto;

    @Column(precision = 12, scale = 2)
    private BigDecimal amount;

    @Column(columnDefinition = "JSONB")
    private String paymentData;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentStatus status = PaymentStatus.PENDING;
}