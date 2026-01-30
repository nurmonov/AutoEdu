package org.example.autoedu.dto.user;

import lombok.Data;

import java.time.LocalDateTime;


@Data
public class UserResponse {
    private Integer id;
    private String fullName;
    private String phoneNumber;
    private String email;
    private String photo;
    private String passportPhoto;
    private String jshshr;
    private String seriaRaqam;
    private String role;
    private String status;
    private LocalDateTime createdAt;
}
