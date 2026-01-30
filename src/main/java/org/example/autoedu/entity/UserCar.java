package org.example.autoedu.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_cars")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserCar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_id_cars_id")
    private Integer userIdCarsId;

    @Column(name = "instructor_cars_id")
    private Integer instructorCarsId;
}
