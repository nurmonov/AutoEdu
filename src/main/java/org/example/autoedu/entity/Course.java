package org.example.autoedu.entity;


import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;


@Entity
@Table(name = "course")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "full_name")
    private String fullName;

    private String title;
    private String description;

    @Column(precision = 12, scale = 2)
    private BigDecimal price;


    private String photo;

    @Column(name = "video_count")
    private Integer videoCount;


    private String logo;

    private String location;
}