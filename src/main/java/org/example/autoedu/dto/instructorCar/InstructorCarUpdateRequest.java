package org.example.autoedu.dto.instructorCar;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InstructorCarUpdateRequest {
    private String fullName;
    private String photo;
    private String number;
}
