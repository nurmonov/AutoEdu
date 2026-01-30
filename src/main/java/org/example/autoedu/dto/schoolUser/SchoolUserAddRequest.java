package org.example.autoedu.dto.schoolUser;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SchoolUserAddRequest {
    private Integer schoolId;
    private Integer userId;
    private String roleInSchool; // "INSTRUCTOR", "ADMIN" va h.k.
}
