// UploadedFileListItemDto — soddalashtirildi, uploadedByUsername olib tashlandi
package org.example.autoedu.dto.file;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Builder
public class UploadedFileListItemDto {
    private Integer id;
    private String originalFileName;
    private String fileUrl;
    private String formattedSize;
    private LocalDateTime uploadedAt;
    private Integer lessonId;       // qo'shimcha ma'lumot uchun
    // lessonTitle kerak bo'lsa qo'shish mumkin, lekin hozircha oddiy saqlaymiz
}