package org.example.autoedu.mapper;

import org.example.autoedu.dto.course.CourseCreateRequest;
import org.example.autoedu.dto.course.CourseResponse;
import org.example.autoedu.entity.Course;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring",
        builder = @Builder(disableBuilder = true))
public interface CourseMapper {

    @Mapping(target = "id", ignore = true)
    Course toEntity(CourseCreateRequest dto);

    CourseResponse toResponse(Course entity);
    List<CourseResponse> toResponseList(List<Course> entities);

//    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
//    @Mapping(target = "id", ignore = true)
//    @Mapping(target = "school", ignore = true)
//    void updateEntity(@MappingTarget Course entity, CourseUpdateRequest dto);
}