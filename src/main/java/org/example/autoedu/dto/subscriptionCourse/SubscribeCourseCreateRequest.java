package org.example.autoedu.dto.subscriptionCourse;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SubscribeCourseCreateRequest {
    private Integer courseId;
    private Integer subscribeId;
}