package org.example.autoedu.dto.course;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class CourseUpdateRequest {
    private String fullName;
    private String title;
    private String description;
    private BigDecimal price;
    private String photo;
    private Integer videoCount;
    private String logo;
    private String location;
}
