package org.example.autoedu.mapper;

import org.example.autoedu.dto.lesson.LessonCreateRequest;
import org.example.autoedu.dto.lesson.LessonResponse;
import org.example.autoedu.entity.Lesson;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LessonMapper {

    @Mapping(target = "id", ignore = true)
    Lesson toEntity(LessonCreateRequest dto);

    LessonResponse toResponse(Lesson entity);
    List<LessonResponse> toResponseList(List<Lesson> entities);

//    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
//    @Mapping(target = "id", ignore = true)
//    @Mapping(target = "course", ignore = true)
//    void updateEntity(@MappingTarget Lesson entity, LessonUpdateRequest dto);
}
