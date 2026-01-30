package org.example.autoedu.mapper;

import org.example.autoedu.dto.lessonVideo.LessonVideoCreateRequest;
import org.example.autoedu.dto.lessonVideo.LessonVideoResponse;
import org.example.autoedu.entity.LessonVideo;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LessonVideoMapper {

    @Mapping(target = "id", ignore = true)
    LessonVideo toEntity(LessonVideoCreateRequest dto);

    LessonVideoResponse toResponse(LessonVideo entity);
    List<LessonVideoResponse> toResponseList(List<LessonVideo> entities);


//    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
//    @Mapping(target = "id", ignore = true)
//    @Mapping(target = "lesson", ignore = true)
//    void updateEntity(@MappingTarget LessonVideo entity, LessonVideoUpdateRequest dto);
}
