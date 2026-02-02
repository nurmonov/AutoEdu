package org.example.autoedu.dto.userCar;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserCarAddRequest {
    private Integer userId;
    private Integer instructorCarId;
}
