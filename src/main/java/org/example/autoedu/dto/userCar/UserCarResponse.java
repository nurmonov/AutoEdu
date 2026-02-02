package org.example.autoedu.dto.userCar;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserCarResponse {
    private Integer id;
    private Integer userId;
    private Integer instructorCarId;
}
