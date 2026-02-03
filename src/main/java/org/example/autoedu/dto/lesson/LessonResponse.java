package org.example.autoedu.dto.lesson;


import lombok.Data;

@Data
public class LessonResponse {
    private Integer id;
    private String fullName;
    private String title;
    private String description;
    private String file;
    private Integer courseId;

}