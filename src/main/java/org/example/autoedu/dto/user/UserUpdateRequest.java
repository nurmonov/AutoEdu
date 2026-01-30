package org.example.autoedu.dto.user;

import lombok.Data;

@Data
public class UserUpdateRequest {
    private String fullName;
    private String phoneNumber;
    private String email;
    private String photo;
    private String passportPhoto;
    private String seriaRaqam;
    private String newPassword;
    private String status;
}
