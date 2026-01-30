package org.example.autoedu.dto.login;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {
    @NotBlank(message = "Telefon raqami bo'sh bo'lmasligi kerak")
    private String phoneNumber;

    @NotBlank(message = "Parol bo'sh bo'lmasligi kerak")
    private String password;
}


