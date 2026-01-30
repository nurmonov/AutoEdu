package org.example.autoedu.dto.courseSubscription;


import lombok.Data;

import java.math.BigDecimal;

@Data
public class CourseSubscriptionRequest {

    private Integer userId;

    private Integer courseId;
    private BigDecimal pricePaid;
    private Integer remainingFreeHours;
    private BigDecimal pricePerHour;
}
