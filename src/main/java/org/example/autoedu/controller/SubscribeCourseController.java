package org.example.autoedu.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.autoedu.dto.subscriptionCourse.SubscribeCourseCreateRequest;
import org.example.autoedu.dto.subscriptionCourse.SubscribeCourseResponse;
import org.example.autoedu.service.SubscribeCourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subscribe-courses")
@RequiredArgsConstructor
@Tag(name = "Subscribe Courses", description = "Obuna paketini kursga bog'lash")
@SecurityRequirement(name = "bearerAuth")
public class SubscribeCourseController {

    private final SubscribeCourseService subscribeCourseService;

    @Operation(summary = "Obuna paketiga kurs qushish (admin)")
    @PreAuthorize("hasAnyRole('SCHOOL_ADMIN', 'SUPER_ADMIN')")
    @PostMapping
    public ResponseEntity<SubscribeCourseResponse> addCourseToSubscribe(@RequestBody SubscribeCourseCreateRequest request) {
        SubscribeCourseResponse response = subscribeCourseService.addCourseToSubscribe(
                request.getSubscribeId(), request.getCourseId());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(summary = "Bitta obuna paketidagi kurslarni olish")
    @GetMapping("/subscribe/{subscribeId}")
    public ResponseEntity<List<SubscribeCourseResponse>> getCoursesBySubscribe(@PathVariable Integer subscribeId) {
        return ResponseEntity.ok(subscribeCourseService.getCoursesBySubscribe(subscribeId));
    }
}
