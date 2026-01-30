package org.example.autoedu.dto.subscription;

import lombok.Builder;
import lombok.Data;
import org.example.autoedu.entity.enums.SubscribeData;

import java.math.BigDecimal;

// SubscribeResponse.java
@Data
@Builder
public class SubscribeResponse {
    private Integer id;
    private String fullName;
    private BigDecimal price;
    private String additionals;
    private SubscribeData subscribeData;
    private String sale;
    private String promo;
    private Integer promoId;
}
