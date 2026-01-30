package org.example.autoedu.dto.schoolUser;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SchoolUserRemoveRequest {
    private Integer schoolId;
    private Integer userId;
}
