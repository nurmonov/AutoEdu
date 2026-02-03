package org.example.autoedu.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.autoedu.dto.lessonVideo.LessonVideoCreateRequest;
import org.example.autoedu.dto.lessonVideo.LessonVideoResponse;
import org.example.autoedu.dto.lessonVideo.LessonVideoUpdateRequest;
import org.example.autoedu.service.LessonVideoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lesson-videos")
@RequiredArgsConstructor
@Tag(name = "Lesson Videos", description = "Dars videolari bilan ishlash")
@SecurityRequirement(name = "bearerAuth")
public class LessonVideoController {

    private final LessonVideoService lessonVideoService;

    @Operation(summary = "Barcha dars videolarini olish")
    @GetMapping
    public ResponseEntity<List<LessonVideoResponse>> getAllLessonVideos() {
        return ResponseEntity.ok(lessonVideoService.getAllLessonVideos());
    }

    @Operation(summary = "ID buyicha video malumotini olish")
    @GetMapping("/{id}")
    public ResponseEntity<LessonVideoResponse> getLessonVideoById(@PathVariable Integer id) {
        return ResponseEntity.ok(lessonVideoService.getLessonVideoById(id));
    }

    @Operation(summary = "Bitta darsga tegishli videolarni olish")
    @GetMapping("/lesson/{lessonId}")
    public ResponseEntity<List<LessonVideoResponse>> getVideosByLesson(@PathVariable Integer lessonId) {
        return ResponseEntity.ok(lessonVideoService.getVideosByLessonId(lessonId));
    }

    @Operation(summary = "Yangi dars videosini qushish (admin uchun)")
    @PreAuthorize("hasAnyRole('SCHOOL_ADMIN', 'SUPER_ADMIN')")
    @PostMapping
    public ResponseEntity<LessonVideoResponse> createLessonVideo(@RequestBody LessonVideoCreateRequest request) {
        LessonVideoResponse response = lessonVideoService.createLessonVideo(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(summary = "Dars videosini yangilash (admin uchun)")
    @PreAuthorize("hasAnyRole('SCHOOL_ADMIN', 'SUPER_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<LessonVideoResponse> updateLessonVideo(
            @PathVariable Integer id,
            @RequestBody LessonVideoUpdateRequest request) {
        return ResponseEntity.ok(lessonVideoService.updateLessonVideo(id, request));
    }

    @Operation(summary = "Dars videosini uchirish (admin uchun)")
    @PreAuthorize("hasAnyRole('SCHOOL_ADMIN', 'SUPER_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLessonVideo(@PathVariable Integer id) {
        lessonVideoService.deleteLessonVideo(id);
        return ResponseEntity.noContent().build();
    }
}