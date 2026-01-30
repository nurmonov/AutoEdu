package org.example.autoedu.dto.user;

import lombok.Data;

@Data
public class UserShortResponse {
    private Integer id;
    private String fullName;
    private String phoneNumber;
    private String role;
}
