package org.example.autoedu.dto.school;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SchoolResponse {
    private Integer id;
    private String fullName;
    private String status;
    private String phoneNumber;
    private String logo;
    private String location;
}