package org.example.autoedu.mapper;

import org.example.autoedu.dto.usersubscription.UserSubscribeCreateRequest;
import org.example.autoedu.dto.usersubscription.UserSubscribeResponse;
import org.example.autoedu.dto.usersubscription.UserSubscribeUnsubscribeRequest;
import org.example.autoedu.entity.UserSubscribe;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserSubscribeMapper {

    @Mapping(target = "id", ignore = true)
    UserSubscribe toEntity(UserSubscribeCreateRequest dto);

    UserSubscribeResponse toResponse(UserSubscribe entity);
    List<UserSubscribeResponse> toResponseList(List<UserSubscribe> entities);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "userId", ignore = true)          // user o'zgarmasligi kerak
    @Mapping(target = "subscribeCourseId", ignore = true) // bog'lanish o'zgarmasligi kerak
    void updateEntity(@MappingTarget UserSubscribe entity, UserSubscribeUnsubscribeRequest dto);
}