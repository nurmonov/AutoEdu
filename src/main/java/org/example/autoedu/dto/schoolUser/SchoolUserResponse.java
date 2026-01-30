package org.example.autoedu.dto.schoolUser;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SchoolUserResponse {
    private Integer schoolId;
    private Integer userId;
    private String roleInSchool;
}
