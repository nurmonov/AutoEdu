package org.example.autoedu.dto.subscriptionCourse;

import lombok.Builder;
import lombok.Data;
import org.example.autoedu.dto.course.CourseResponse;
import org.example.autoedu.dto.subscription.SubscribeResponse;

@Data
@Builder
public class SubscribeCourseFullResponse {
    private Integer id;
    private SubscribeResponse subscribe;  // obuna paketi
    private CourseResponse course;        // kurs ma'lumotlari
}