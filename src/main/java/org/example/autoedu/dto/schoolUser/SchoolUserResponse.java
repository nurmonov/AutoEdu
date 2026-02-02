package org.example.autoedu.dto.schoolUser;

import lombok.*;
import org.example.autoedu.dto.school.SchoolResponse;
import org.example.autoedu.dto.user.UserResponse;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class SchoolUserResponse {
    private Integer schoolId;
    private SchoolResponse school;  // to'liq maktab ma'lumotlari
    private Integer userId;
    private UserResponse user;      // to'liq user ma'lumotlari
    // private String roleInSchool; // agar role qo'shmoqchi bo'lsangiz
}