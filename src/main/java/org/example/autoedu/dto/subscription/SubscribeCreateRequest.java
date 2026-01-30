package org.example.autoedu.dto.subscription;

import lombok.Builder;
import lombok.Data;
import org.example.autoedu.entity.enums.SubscribeData;

import java.math.BigDecimal;


// SubscribeCreateRequest.java
@Data @Builder
public class SubscribeCreateRequest {
    private String fullName;
    private BigDecimal price;
    private String additionals;
    private SubscribeData subscribeData;
    private String sale;
    private String promo;
    private Integer promoId;
}

