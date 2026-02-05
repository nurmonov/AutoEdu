// UploadedFileResponseDto — eng to'liq ma'lumot, uploadedByUsername olib tashlandi
package org.example.autoedu.dto.file;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Builder
public class UploadedFileResponseDto {
    private Integer id;
    private String originalFileName;
    private String storedFileName;
    private String contentType;
    private Long fileSize;              // Long bo'lishi yaxshi
    private String fileUrl;
    private String formattedSize;
    private LocalDateTime uploadedAt;

    // bog'lanishlar
    private Integer lessonId;
    private String lessonTitle;         // lesson.title
    private Integer courseId;           // lesson.course.id
    private String courseTitle;         // lesson.course.title

    // qo'shimcha holatlar
    private boolean isImage;
    private boolean isPdf;
}