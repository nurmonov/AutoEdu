package org.example.autoedu.dto.user;

import lombok.Data;

@Data
public class UserCreateRequest {

    private String fullName;
    private String phoneNumber;
    private String email;
    private String password;
    private String photo;
    private String passportPhoto;
    private String jshshr;
    private String seriaRaqam;
    private String role;
    private String status;
}