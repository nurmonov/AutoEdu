package org.example.autoedu.dto.lVideo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LVideoAddRequest {
    private Integer lessonId;
    private Integer lessonVideoId;
}
