package org.example.autoedu.dto.course;

import lombok.*;

import java.math.BigDecimal;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
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