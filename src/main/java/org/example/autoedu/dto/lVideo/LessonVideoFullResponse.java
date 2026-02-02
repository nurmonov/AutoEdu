package org.example.autoedu.dto.lVideo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LessonVideoFullResponse {
    private Integer id;
    private String fullName;
    private String title;
    private String description;
    private String videoUrl;
    private String photoUrl;
    private Integer lessonId;
}
