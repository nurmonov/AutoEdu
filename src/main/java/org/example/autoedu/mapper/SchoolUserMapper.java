package org.example.autoedu.mapper;

import org.example.autoedu.dto.schoolUser.SchoolUserAddRequest;
import org.example.autoedu.dto.schoolUser.SchoolUserResponse;
import org.example.autoedu.dto.schoolUser.SchoolUserUpdateRequest;
import org.example.autoedu.entity.SchoolUser;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SchoolUserMapper {

    // Oddiy mapping (faqat ID'lar)
    @Mapping(target = "schoolId", source = "id.schoolId")
    @Mapping(target = "userId", source = "id.userId")
    // .roleInSchool agar maydon bo'lsa qo'shing
    SchoolUserResponse toResponse(SchoolUser entity);

    List<SchoolUserResponse> toResponseList(List<SchoolUser> entities);

    // Agar create uchun kerak bo'lsa
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "school", ignore = true)
    @Mapping(target = "user", ignore = true)
    SchoolUser toEntity(SchoolUserAddRequest request);

    // Agar update kerak bo'lsa (masalan roleInSchool uchun)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(@MappingTarget SchoolUser entity, SchoolUserUpdateRequest request);

    // Qo'shimcha: to'liq obyekt qaytarish uchun custom mapping
    @AfterMapping
    default void enrichWithFullObjects(@MappingTarget SchoolUserResponse target, SchoolUser source) {
        // Bu yerda service'da to'ldiriladi yoki agar mapperda olish mumkin bo'lsa
    }
}
