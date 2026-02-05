package org.example.autoedu.mapper;

import org.example.autoedu.dto.lesson.LessonCreateRequest;
import org.example.autoedu.dto.lesson.LessonResponse;
import org.example.autoedu.dto.lesson.LessonUpdateRequest;
import org.example.autoedu.entity.Lesson;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LessonMapper {

    @Mapping(target = "course", ignore = true)  // create da service qo'yadi
    Lesson toEntity(LessonCreateRequest request);

    // agar courseId ham kerak bo'lsa:
    @Mapping(target = "courseId", source = "course.id")
    LessonResponse toResponse(Lesson lesson);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "course", ignore = true)
    void updateFromRequest(LessonUpdateRequest request, @MappingTarget Lesson lesson);

    List<LessonResponse> toResponseList(List<Lesson> lessons);
}