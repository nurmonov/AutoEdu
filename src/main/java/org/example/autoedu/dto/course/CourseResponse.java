package org.example.autoedu.dto.course;

import lombok.Data;

import java.math.BigDecimal;


@Data
public class CourseResponse {
    private Integer id;
    private String fullName;
    private String title;
    private String description;
    private BigDecimal price;
    private String photo;
    private Integer videoCount;
    private String logo;
    private String location;
    private Integer schoolId;
}