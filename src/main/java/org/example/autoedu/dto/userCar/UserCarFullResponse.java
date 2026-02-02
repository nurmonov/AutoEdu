package org.example.autoedu.dto.userCar;


import lombok.Builder;
import lombok.Data;
import org.example.autoedu.dto.instructorCar.InstructorCarResponse;
import org.example.autoedu.dto.user.UserResponse;

@Data
@Builder
public class UserCarFullResponse {
    private Integer id;
    private UserResponse user;               // user ma'lumotlari
    private InstructorCarResponse instructorCar;  // instruktor mashinasi ma'lumotlari
}
