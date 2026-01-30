package org.example.autoedu.dto.lessonVideo;


import lombok.Data;

@Data
public class LessonVideoCreateRequest {
    private String fullName;
    private String title;
    private String description;
    private String videoUrl;
    private String photoUrl;
    private Integer lessonId;
}
