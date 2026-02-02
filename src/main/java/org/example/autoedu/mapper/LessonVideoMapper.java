package org.example.autoedu.mapper;

import org.example.autoedu.dto.lVideo.LessonVideoFullResponse;
import org.example.autoedu.dto.lessonVideo.LessonVideoCreateRequest;
import org.example.autoedu.dto.lessonVideo.LessonVideoResponse;
import org.example.autoedu.entity.LessonVideo;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LessonVideoMapper {
    LessonVideoFullResponse toFullResponse(LessonVideo entity);
    List<LessonVideoFullResponse> toFullResponseList(List<LessonVideo> entities);
}