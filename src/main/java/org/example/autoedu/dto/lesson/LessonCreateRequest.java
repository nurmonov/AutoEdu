package org.example.autoedu.dto.lesson;

import lombok.Data;


@Data
public class LessonCreateRequest {
    private String fullName;
    private String title;
    private String description;
    private String file;
    private Integer courseId;

}
