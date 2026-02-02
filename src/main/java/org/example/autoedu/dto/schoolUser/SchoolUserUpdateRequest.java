package org.example.autoedu.dto.schoolUser;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SchoolUserUpdateRequest {
    private String roleInSchool;  // faqat shu maydonni yangilash uchun (masalan "INSTRUCTOR" → "ADMIN")
}