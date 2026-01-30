package org.example.autoedu.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.autoedu.dto.lVideo.LVideoAddRequest;
import org.example.autoedu.dto.lVideo.LVideoRemoveRequest;
import org.example.autoedu.dto.lVideo.LVideoResponse;
import org.example.autoedu.service.LVideoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lvideos")
@RequiredArgsConstructor
@Tag(name = "L Videos", description = "Dars va videolarni bog'lash (junction table)")
@SecurityRequirement(name = "bearerAuth")
public class LVideoController {

    private final LVideoService lVideoService;

    @Operation(summary = "Bitta darsga qo'shilgan videolarni olish")
    @GetMapping("/lesson/{lessonId}")
    public ResponseEntity<List<LVideoResponse>> getVideosByLesson(@PathVariable Integer lessonId) {
        return ResponseEntity.ok(lVideoService.getVideosByLesson(lessonId));
    }

    @Operation(summary = "Darsga yangi video qo'shish (admin)")
    @PreAuthorize("hasAnyRole('SCHOOL_ADMIN', 'SUPER_ADMIN')")
    @PostMapping
    public ResponseEntity<LVideoResponse> addVideoToLesson(@RequestBody LVideoAddRequest request) {
        LVideoResponse response = lVideoService.addVideoToLesson(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(summary = "Darsdan videoni o'chirish (admin)")
    @PreAuthorize("hasAnyRole('SCHOOL_ADMIN', 'SUPER_ADMIN')")
    @DeleteMapping
    public ResponseEntity<Void> removeVideoFromLesson(@RequestBody LVideoRemoveRequest request) {
        lVideoService.removeVideoFromLesson(request.getLessonId(), request.getLessonVideoId());
        return ResponseEntity.noContent().build();
    }
}
