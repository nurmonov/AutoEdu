package org.example.autoedu.dto.instructorCar;

import lombok.Data;


@Data
public class InstructorCarRequest {

    private Integer instructorId;
    private String fullName;
    private String photo;
    private String number;
}
