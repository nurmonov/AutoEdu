package org.example.autoedu.mapper;

import org.example.autoedu.dto.lesson.LessonCreateRequest;
import org.example.autoedu.dto.lesson.LessonResponse;
import org.example.autoedu.dto.lesson.LessonUpdateRequest;
import org.example.autoedu.entity.Lesson;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LessonMapper {

    @Mapping(target = "course", ignore = true)  // course ni service da qo'yamiz
    Lesson toEntity(LessonCreateRequest request);

    LessonResponse toResponse(Lesson lesson);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "course", ignore = true)
    void updateFromRequest(LessonUpdateRequest request, @MappingTarget Lesson lesson);

    List<LessonResponse> toResponseList(List<Lesson> lessons);
}