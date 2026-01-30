package org.example.autoedu.entity;


import jakarta.persistence.*;
import lombok.*;
import org.example.autoedu.entity.enums.SubscribeData;

import java.math.BigDecimal;

@Entity
@Table(name = "subscribe")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Subscribe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "full_name")
    private String fullName;


    private BigDecimal price;

    private String additionals;

    @Enumerated(EnumType.STRING)
    private SubscribeData subscribeData;

    private String sale;
    private String promo;

    @Column(name = "promo_id")
    private Integer promoId;
}
