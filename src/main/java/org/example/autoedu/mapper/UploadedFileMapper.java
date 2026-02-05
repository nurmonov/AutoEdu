package org.example.autoedu.mapper;

import org.example.autoedu.dto.file.UploadedFileListItemDto;
import org.example.autoedu.dto.file.UploadedFileResponseDto;
import org.example.autoedu.entity.UploadedFile;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UploadedFileMapper {

    @Mapping(target = "fileUrl", ignore = true)           // service da qo'yiladi
    @Mapping(target = "formattedSize", ignore = true)     // service da hisoblanadi
    @Mapping(target = "lessonId", source = "lesson.id")
    @Mapping(target = "lessonTitle", source = "lesson.title")
    @Mapping(target = "courseId", source = "lesson.course.id")
    @Mapping(target = "courseTitle", source = "lesson.course.title")
    UploadedFileResponseDto toResponse(UploadedFile file);

    @Mapping(target = "fileUrl", ignore = true)
    @Mapping(target = "formattedSize", ignore = true)
    @Mapping(target = "lessonId", source = "lesson.id")
    UploadedFileListItemDto toListItem(UploadedFile file);

    List<UploadedFileListItemDto> toListItemList(List<UploadedFile> files);

    // Agar update kerak bo'lsa (hozircha yo'q)
}