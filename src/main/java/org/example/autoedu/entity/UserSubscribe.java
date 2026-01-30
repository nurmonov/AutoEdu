package org.example.autoedu.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_subscribe")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserSubscribe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "subscribe_course_id")
    private Integer subscribeCourseId;

    private Boolean isSubscribed;
}
