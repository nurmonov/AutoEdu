package org.example.autoedu.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.autoedu.dto.lesson.LessonCreateRequest;
import org.example.autoedu.dto.lesson.LessonResponse;
import org.example.autoedu.dto.lesson.LessonUpdateRequest;
import org.example.autoedu.service.LessonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lessons")
@RequiredArgsConstructor
@Tag(name = "Lessons", description = "Darslar bilan ishlash")
@SecurityRequirement(name = "bearerAuth")
public class LessonController {

    private final LessonService lessonService;

    @Operation(summary = "Barcha darslarni olish")
    @GetMapping
    public ResponseEntity<List<LessonResponse>> getAllLessons() {
        return ResponseEntity.ok(lessonService.getAllLessons());
    }

    @Operation(summary = "ID bo'yicha dars ma'lumotini olish")
    @GetMapping("/{id}")
    public ResponseEntity<LessonResponse> getLessonById(@PathVariable Integer id) {
        return ResponseEntity.ok(lessonService.getLessonById(id));
    }

    @Operation(summary = "Yangi dars qo'shish (admin uchun)")
    @PreAuthorize("hasAnyRole('SCHOOL_ADMIN', 'SUPER_ADMIN')")
    @PostMapping
    public ResponseEntity<LessonResponse> createLesson(@RequestBody LessonCreateRequest request) {
        LessonResponse response = lessonService.createLesson(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(summary = "Dars ma'lumotini yangilash (admin uchun)")
    @PreAuthorize("hasAnyRole('SCHOOL_ADMIN', 'SUPER_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<LessonResponse> updateLesson(
            @PathVariable Integer id,
            @RequestBody LessonUpdateRequest request) {
        return ResponseEntity.ok(lessonService.updateLesson(id, request));
    }

    @Operation(summary = "Darsni o'chirish (admin uchun)")
    @PreAuthorize("hasAnyRole('SCHOOL_ADMIN', 'SUPER_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLesson(@PathVariable Integer id) {
        lessonService.deleteLesson(id);
        return ResponseEntity.noContent().build();
    }
}
