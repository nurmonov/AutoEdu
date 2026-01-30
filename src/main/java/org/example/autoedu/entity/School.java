package org.example.autoedu.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.autoedu.entity.enums.Status;


@Entity
@Table(name = "school")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class School {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "full_name")
    private String fullName;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "phone_number")
    private String phoneNumber;


    private String logo;

    private String location;
}