package org.example.autoedu.dto.usersubscription;

import lombok.*;
import org.example.autoedu.dto.subscriptionCourse.SubscribeCourseResponse;
import org.example.autoedu.dto.user.UserResponse;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserSubscribeResponse {
    private Integer id;
    private UserResponse user;
    private SubscribeCourseResponse subscribeCourse;
    private Boolean isSubscribed;
}