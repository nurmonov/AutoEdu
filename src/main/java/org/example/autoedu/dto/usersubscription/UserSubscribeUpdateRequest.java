package org.example.autoedu.dto.usersubscription;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserSubscribeUpdateRequest {
    private Boolean isSubscribed;
}
