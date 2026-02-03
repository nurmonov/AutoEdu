package org.example.autoedu.dto.lesson;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LessonUpdateRequest {
    private String fullName;
    private String title;
    private String description;
    private String file;
 }