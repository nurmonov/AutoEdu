package org.example.autoedu.dto.file;

import lombok.Data;

// Yuklash uchun kiruvchi DTO (faqat kerakli maydonlar)
@Data
public class UploadRequestDto {
    private String title;          // ixtiyoriy
    private String description;    // ixtiyoriy
}

