package org.example.autoedu.dto.usersubscription;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserSubscribeUnsubscribeRequest {
    private Integer userId;
    private Integer subscribeCourseId;
}
