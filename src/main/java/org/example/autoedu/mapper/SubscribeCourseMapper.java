package org.example.autoedu.mapper;

import org.example.autoedu.dto.subscriptionCourse.SubscribeCourseCreateRequest;
import org.example.autoedu.dto.subscriptionCourse.SubscribeCourseResponse;
import org.example.autoedu.dto.subscriptionCourse.SubscribeCourseUpdateRequest;
import org.example.autoedu.entity.SubscribeCourse;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SubscribeCourseMapper {

    @Mapping(target = "id", ignore = true)
    SubscribeCourse toEntity(SubscribeCourseCreateRequest dto);

    SubscribeCourseResponse toResponse(SubscribeCourse entity);
    List<SubscribeCourseResponse> toResponseList(List<SubscribeCourse> entities);

//    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
//    @Mapping(target = "id", ignore = true)
//    void updateEntity(@MappingTarget SubscribeCourse entity, SubscribeCourseUpdateRequest dto);
}
