package org.example.autoedu.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "instructor_cars")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InstructorCar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "instruktor_id")
    private Integer instruktorId;

    @Column(name = "full_name")
    private String fullName;


    private String photo;

    private String number;


}
