package org.example.autoedu.controller;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.example.autoedu.dto.file.FileUploadRequestDto;
import org.example.autoedu.dto.file.UploadedFileListItemDto;
import org.example.autoedu.dto.file.UploadedFileResponseDto;
import org.example.autoedu.service.FileService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/files")
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;

    /**
     * Bir yoki bir nechta faylni darsga yuklash
     * Endpoint: POST /api/files/lesson/{lessonId}/upload
     */
    @PostMapping(value = "/lessons/{lessonId}/files", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<List<UploadedFileResponseDto>> uploadFilesToLesson(
            @PathVariable Integer lessonId,

            @RequestPart("files") MultipartFile[] files,   // faqat fayllar uchun @RequestPart

            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "replaceIfExists", required = false, defaultValue = "false") Boolean replaceIfExists,
            @RequestParam(value = "notifyStudents", required = false, defaultValue = "false") Boolean notifyStudents
    ) {
        FileUploadRequestDto request = new FileUploadRequestDto();
        request.setTitle(title);
        request.setDescription(description);
        request.setReplaceIfExists(replaceIfExists);
        request.setNotifyStudents(notifyStudents);

        List<UploadedFileResponseDto> result = fileService.uploadFilesToLesson(files, lessonId, request);

        return ResponseEntity.ok(result);
    }

    /**
     * Mavjud faylni yangilash (o'rniga yangi fayl yuklash)
     * Endpoint: PUT /api/files/{fileId}
     */
    @PutMapping(value = "/{fileId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<UploadedFileResponseDto> updateFile(
            @PathVariable Integer fileId,

            @RequestPart(value = "file", required = false) MultipartFile newFile,   // fayl uchun @RequestPart saqlaymiz

            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "replaceIfExists", required = false, defaultValue = "false") Boolean replaceIfExists,
            @RequestParam(value = "notifyStudents", required = false, defaultValue = "false") Boolean notifyStudents
    ) throws IOException {

        FileUploadRequestDto request = new FileUploadRequestDto();
        request.setTitle(title);
        request.setDescription(description);
        request.setReplaceIfExists(replaceIfExists);
        request.setNotifyStudents(notifyStudents);

        UploadedFileResponseDto updated = fileService.updateFile(fileId, newFile, request);

        return ResponseEntity.ok(updated);
    }

    /**
     * Muayyan darsdagi barcha fayllarni olish (ro'yxat uchun yengil DTO)
     * Endpoint: GET /api/files/lesson/{lessonId}
     */
    @GetMapping("/lesson/{lessonId}")
    public ResponseEntity<List<UploadedFileListItemDto>> getFilesByLesson(
            @PathVariable Integer lessonId) {

        List<UploadedFileListItemDto> files = fileService.getFilesByLesson(lessonId);
        return ResponseEntity.ok(files);
    }

    /**
     * Bitta fayl haqida to'liq ma'lumot olish
     * Endpoint: GET /api/files/{fileId}
     */
    @GetMapping("/{fileId}")
    public ResponseEntity<UploadedFileResponseDto> getFileById(
            @PathVariable Integer fileId) {

        UploadedFileResponseDto dto = fileService.getFile(fileId);
        return ResponseEntity.ok(dto);
    }

    /**
     * Faylni yuklab olish (agar kerak bo'lsa, alohida endpoint)
     * Endpoint: GET /api/files/download/{storedFileName}
     */
    // @GetMapping("/download/{storedFileName:.+}")
    // public ResponseEntity<Resource> downloadFile(@PathVariable String storedFileName) {
    //     // Implementatsiya kerak bo'lsa, keyinroq qo'shamiz
    //     // Hozircha faqat fileUrl orqali yuklab olish mumkin
    // }
}