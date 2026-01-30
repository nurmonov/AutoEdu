package org.example.autoedu.dto.courseSubscription;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class CourseSubscriptionUpdateRequest {
    private String status;
    private BigDecimal pricePaid;
    private Integer remainingFreeHours;
    private BigDecimal pricePerHour;
}
