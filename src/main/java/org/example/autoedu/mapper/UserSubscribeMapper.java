package org.example.autoedu.mapper;

import org.example.autoedu.dto.subscriptionCourse.SubscribeCourseResponse;
import org.example.autoedu.dto.user.UserResponse;
import org.example.autoedu.dto.usersubscription.UserSubscribeCreateRequest;
import org.example.autoedu.dto.usersubscription.UserSubscribeResponse;
import org.example.autoedu.dto.usersubscription.UserSubscribeUnsubscribeRequest;
import org.example.autoedu.dto.usersubscription.UserSubscribeUpdateRequest;
import org.example.autoedu.entity.UserSubscribe;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", uses = {UserMapper.class, SubscribeCourseMapper.class})
public interface UserSubscribeMapper {

    @Mapping(target = "user", source = "userId", qualifiedByName = "userIdToUserResponse")
    @Mapping(target = "subscribeCourse", source = "subscribeCourseId", qualifiedByName = "subscribeCourseIdToSubscribeCourseResponse")
    UserSubscribeResponse toResponse(UserSubscribe entity);

    List<UserSubscribeResponse> toResponseList(List<UserSubscribe> entities);

    // Agar create uchun kerak bo'lsa
    @Mapping(target = "id", ignore = true)
    UserSubscribe toEntity(UserSubscribeCreateRequest request);

    // Qo'shimcha: agar update uchun kerak bo'lsa
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(@MappingTarget UserSubscribe entity, UserSubscribeUpdateRequest request);

    // Custom mapping metodlari (agar kerak bo'lsa)
    @Named("userIdToUserResponse")
    default UserResponse userIdToUserResponse(Integer userId) {
        // Bu yerda userRepository orqali olish mumkin, lekin service'da yaxshiroq
        return null; // service'da to'ldiramiz
    }

    @Named("subscribeCourseIdToSubscribeCourseResponse")
    default SubscribeCourseResponse subscribeCourseIdToSubscribeCourseResponse(Integer subscribeCourseId) {
        return null; // service'da to'ldiramiz
    }
}