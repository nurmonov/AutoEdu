package org.example.autoedu.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "subscribe_course")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubscribeCourse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "course_id")
    private Integer courseId;

    @Column(name = "subscribe_id")
    private Integer subscribeId;
}
