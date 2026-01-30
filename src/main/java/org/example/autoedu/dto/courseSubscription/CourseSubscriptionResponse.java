package org.example.autoedu.dto.courseSubscription;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Data
public class CourseSubscriptionResponse {
    private Long id;
    private Long userId;
    private Long courseId;
    private LocalDateTime subscribedAt;
    private String status;
    private BigDecimal pricePaid;
    private Integer remainingFreeHours;
    private BigDecimal pricePerHour;
}
