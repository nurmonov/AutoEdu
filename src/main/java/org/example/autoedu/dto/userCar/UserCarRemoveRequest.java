package org.example.autoedu.dto.userCar;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserCarRemoveRequest {
    private Integer userId;
    private Integer instructorCarId;
}
