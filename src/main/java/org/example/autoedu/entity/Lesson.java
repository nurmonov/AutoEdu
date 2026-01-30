package org.example.autoedu.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "lessons")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "full_name")
    private String fullName;

    private String title;
    private String description;

    private String file;
}
