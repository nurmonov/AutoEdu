package org.example.autoedu.mapper;

import org.example.autoedu.dto.course.CourseCreateRequest;
import org.example.autoedu.dto.course.CourseResponse;
import org.example.autoedu.dto.course.CourseUpdateRequest;
import org.example.autoedu.entity.Course;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CourseMapper {

    Course toEntity(CourseCreateRequest request);

    @Mapping(target = "schoolId", source = "school.id")   // <-- BU QATORNI QO'SHING!
    CourseResponse toResponse(Course course);

    @Mapping(target = "id", ignore = true)
    void updateFromRequest(CourseUpdateRequest request, @MappingTarget Course course);

    List<CourseResponse> toResponseList(List<Course> courses);
}