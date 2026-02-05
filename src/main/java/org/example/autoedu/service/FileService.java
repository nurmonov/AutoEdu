package org.example.autoedu.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.autoedu.dto.file.FileUploadRequestDto;
import org.example.autoedu.dto.file.UploadedFileListItemDto;
import org.example.autoedu.dto.file.UploadedFileResponseDto;
import org.example.autoedu.entity.Lesson;
import org.example.autoedu.entity.UploadedFile;
import org.example.autoedu.mapper.UploadedFileMapper;
import org.example.autoedu.repo.LessonRepository;
import org.example.autoedu.repo.UploadedFileRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FileService {

    private static final String UPLOAD_DIR = "uploads/";

    private final UploadedFileRepository uploadedFileRepository;
    private final LessonRepository lessonRepository;
    private final UploadedFileMapper uploadedFileMapper;

    @Transactional
    public List<UploadedFileResponseDto> uploadFilesToLesson(
            MultipartFile[] files,
            Integer lessonId,
            FileUploadRequestDto requestDto  // courseId yo'q, faqat lessonId
    ) {
        if (files == null || files.length == 0) {
            throw new IllegalArgumentException("Hech qanday fayl tanlanmagan");
        }

        Lesson lesson = lessonRepository.findById(lessonId)
                .orElseThrow(() -> new IllegalArgumentException("Dars topilmadi: " + lessonId));

        return List.of(files).stream()
                .filter(file -> !file.isEmpty())
                .map(file -> saveSingleFile(file, lesson, requestDto))
                .collect(Collectors.toList());
    }

    private UploadedFileResponseDto saveSingleFile(
            MultipartFile file,
            Lesson lesson,
            FileUploadRequestDto requestDto
    ) {
        try {
            String originalFilename = file.getOriginalFilename();
            String extension = "";
            if (originalFilename != null && originalFilename.contains(".")) {
                extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            }

            String storedFilename = UUID.randomUUID() + extension;
            Path targetPath = Paths.get(UPLOAD_DIR + storedFilename);

            Files.createDirectories(targetPath.getParent());
            Files.copy(file.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);

            UploadedFile entity = UploadedFile.builder()
                    .originalFileName(originalFilename)
                    .storedFileName(storedFilename)
                    .filePath(targetPath.toString())
                    .contentType(file.getContentType())
                    .fileSize(file.getSize())  // Long
                    .uploadDate(LocalDateTime.now())
                    .lesson(lesson)  // faqat lesson
                    .build();

            uploadedFileRepository.save(entity);

            return toResponseDto(entity);

        } catch (IOException e) {
            throw new RuntimeException("Fayl saqlashda xato: " + file.getOriginalFilename(), e);
        }
    }

    @Transactional
    public UploadedFileResponseDto updateFile(
            Integer fileId,
            MultipartFile newFile,
            FileUploadRequestDto requestDto
    ) throws IOException {
        UploadedFile file = uploadedFileRepository.findById(fileId)
                .orElseThrow(() -> new RuntimeException("Fayl topilmadi: " + fileId));

        // uploadedBy yo'q bo'lgani uchun huquq tekshiruvi olib tashlandi
        // Agar huquq tekshiruvi kerak bo'lsa, lesson.course.owner orqali qilish mumkin

        if (newFile != null && !newFile.isEmpty()) {
            Path oldPath = Paths.get(file.getFilePath());
            Files.deleteIfExists(oldPath);

            String originalFilename = newFile.getOriginalFilename();
            String extension = "";
            if (originalFilename != null && originalFilename.contains(".")) {
                extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            }

            String storedFilename = UUID.randomUUID() + extension;
            Path targetPath = Paths.get(UPLOAD_DIR + storedFilename);

            Files.copy(newFile.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);

            file.setOriginalFileName(originalFilename);
            file.setStoredFileName(storedFilename);
            file.setFilePath(targetPath.toString());
            file.setContentType(newFile.getContentType());
            file.setFileSize(newFile.getSize());
        }

        file.setUploadDate(LocalDateTime.now());
        uploadedFileRepository.save(file);

        return toResponseDto(file);
    }

    public List<UploadedFileListItemDto> getFilesByLesson(Integer lessonId) {
        return uploadedFileRepository.findByLessonId(lessonId).stream()
                .map(this::toListItemDto)
                .collect(Collectors.toList());
    }

    public UploadedFileResponseDto getFile(Integer fileId) {
        UploadedFile file = uploadedFileRepository.findById(fileId)
                .orElseThrow(() -> new RuntimeException("Fayl topilmadi: " + fileId));

        return toResponseDto(file);
    }

    // ────────────────────────────────────────────────
    // DTO conversion yordamchi metodlari (uploadedBy yo'q)
    // ────────────────────────────────────────────────

    private UploadedFileResponseDto toResponseDto(UploadedFile entity) {
        String fileUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/files/")
                .path(entity.getStoredFileName())
                .toUriString();

        String formattedSize = formatFileSize(entity.getFileSize());

        boolean isImage = entity.getContentType() != null &&
                entity.getContentType().startsWith("image/");

        boolean isPdf = "application/pdf".equals(entity.getContentType());

        return UploadedFileResponseDto.builder()
                .id(entity.getId())
                .originalFileName(entity.getOriginalFileName())
                .storedFileName(entity.getStoredFileName())
                .contentType(entity.getContentType())
                .fileSize(entity.getFileSize())
                .fileUrl(fileUrl)
                .formattedSize(formattedSize)
                .uploadedAt(entity.getUploadDate())
                .lessonId(entity.getLesson().getId())
                .lessonTitle(entity.getLesson().getTitle())
                .courseId(entity.getLesson().getCourse().getId())
                .courseTitle(entity.getLesson().getCourse().getTitle())
                .isImage(isImage)
                .isPdf(isPdf)
                .build();
    }

    private UploadedFileListItemDto toListItemDto(UploadedFile entity) {
        String fileUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/files/")
                .path(entity.getStoredFileName())
                .toUriString();

        String formattedSize = formatFileSize(entity.getFileSize());

        return UploadedFileListItemDto.builder()
                .id(entity.getId())
                .originalFileName(entity.getOriginalFileName())
                .fileUrl(fileUrl)
                .formattedSize(formattedSize)
                .uploadedAt(entity.getUploadDate())
                .lessonId(entity.getLesson().getId())  // qo'shildi
                .build();
    }

    private String formatFileSize(long bytes) {
        if (bytes <= 0) return "0 B";
        final String[] units = new String[]{"B", "KB", "MB", "GB", "TB"};
        int digitGroups = (int) (Math.log10(bytes) / Math.log10(1024));
        return String.format("%.1f %s", bytes / Math.pow(1024, digitGroups), units[digitGroups]);
    }
}