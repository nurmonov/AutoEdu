package org.example.autoedu.dto.usersubscription;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserSubscribeCreateRequest {
    private Integer userId;
    private Integer subscribeCourseId;
    private Boolean isSubscribed;
}
