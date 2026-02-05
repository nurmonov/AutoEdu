// FileUploadRequestDto — faqat lessonId majburiy
package org.example.autoedu.dto.file;

import lombok.Data;

@Data
public class FileUploadRequestDto {
    private String title;           // ixtiyoriy — fayl sarlavhasi
    private String description;     // ixtiyoriy

    private Integer lessonId;       // MAJBURIY — fayl qaysi darsga tegishli
    private Boolean replaceIfExists = false;
    private Boolean notifyStudents  = false;
}