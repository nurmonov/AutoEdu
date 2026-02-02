package org.example.autoedu.mapper;


import org.example.autoedu.dto.instructorCar.InstructorCarResponse;
import org.example.autoedu.dto.user.UserResponse;
import org.example.autoedu.dto.userCar.UserCarAddRequest;
import org.example.autoedu.dto.userCar.UserCarFullResponse;
import org.example.autoedu.dto.userCar.UserCarResponse;
import org.example.autoedu.entity.User;
import org.example.autoedu.entity.UserCar;
import org.example.autoedu.entity.InstructorCar;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserCarMapper {

    // Oddiy response (faqat ID'lar)
    @Mapping(target = "userId", source = "userIdCarsId")
    @Mapping(target = "instructorCarId", source = "instructorCarsId")
    UserCarResponse toResponse(UserCar entity);

    List<UserCarResponse> toResponseList(List<UserCar> entities);

    // Create uchun entity
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "userIdCarsId", source = "userId")
    @Mapping(target = "instructorCarsId", source = "instructorCarId")
    UserCar toEntity(UserCarAddRequest request);

    // Agar update kerak bo'lsa (hozircha faqat mavjud bo'lsa)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(@MappingTarget UserCar entity, UserCarAddRequest request);

    // To'liq response (user + car obyektlari bilan)
    @Mapping(target = "user", source = "userIdCarsId", qualifiedByName = "userIdToUserResponse")
    @Mapping(target = "instructorCar", source = "instructorCarsId", qualifiedByName = "instructorCarIdToInstructorCarResponse")
    UserCarFullResponse toFullResponse(UserCar entity);

    // Custom mapping: userId → UserResponse
    @Named("userIdToUserResponse")
    default UserResponse userIdToUserResponse(Integer userId) {
        // Bu yerda service'da to'ldiriladi yoki agar repository bo'lsa chaqiriladi
        // Hozircha null qoldiramiz, service'da to'ldiramiz
        return null;
    }

    // Custom mapping: instructorCarsId → InstructorCarResponse
    @Named("instructorCarIdToInstructorCarResponse")
    default InstructorCarResponse instructorCarIdToInstructorCarResponse(Integer instructorCarId) {
        return null; // service'da to'ldiramiz
    }
}
