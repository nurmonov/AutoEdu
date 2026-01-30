package org.example.autoedu.dto.subscription;

import lombok.Builder;
import lombok.Data;
import org.example.autoedu.entity.enums.SubscribeData;

import java.math.BigDecimal;


@Data @Builder
public class SubscribeUpdateRequest {
    private String fullName;
    private BigDecimal price;
    private String additionals;
    private SubscribeData subscribeData;
    private String sale;
    private String promo;
    private Integer promoId;

}