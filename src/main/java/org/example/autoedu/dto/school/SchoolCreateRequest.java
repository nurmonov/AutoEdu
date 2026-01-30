package org.example.autoedu.dto.school;

import lombok.Data;


@Data
public class SchoolCreateRequest {

    private String fullName;
    private String status;
    private String phoneNumber;
    private String logo;
    private String location;
}
