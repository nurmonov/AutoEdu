package org.example.autoedu.dto.lessonVideo;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LessonVideoResponse {
    private Integer id;
    private String fullName;
    private String title;
    private String description;
    private String videoUrl;
    private String photoUrl;
    private Integer lessonId;
}
