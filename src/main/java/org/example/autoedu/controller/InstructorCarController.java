package org.example.autoedu.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.autoedu.dto.instructorCar.InstructorCarRequest;
import org.example.autoedu.dto.instructorCar.InstructorCarResponse;
import org.example.autoedu.dto.instructorCar.InstructorCarUpdateRequest;
import org.example.autoedu.service.InstructorCarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/instructor-cars")
@RequiredArgsConstructor
@Tag(name = "Instructor Cars", description = "Instruktor mashinalari bilan ishlash")
@SecurityRequirement(name = "bearerAuth")
public class InstructorCarController {

    private final InstructorCarService instructorCarService;

    @Operation(summary = "Barcha instruktor mashinalarini olish")
    @GetMapping
    public ResponseEntity<List<InstructorCarResponse>> getAllInstructorCars() {
        return ResponseEntity.ok(instructorCarService.getAllInstructorCars());
    }

    @Operation(summary = "ID buyicha mashina malumotini olish")
    @GetMapping("/{id}")
    public ResponseEntity<InstructorCarResponse> getInstructorCarById(@PathVariable Integer id) {
        return ResponseEntity.ok(instructorCarService.getInstructorCarById(id));
    }

    @Operation(summary = "Yangi mashina qushish (admin/instruktor uchun)")
    @PreAuthorize("hasAnyRole('SCHOOL_ADMIN', 'SUPER_ADMIN', 'INSTRUCTOR')")
    @PostMapping
    public ResponseEntity<InstructorCarResponse> createInstructorCar(@RequestBody InstructorCarRequest request) {
        InstructorCarResponse response = instructorCarService.createInstructorCar(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(summary = "Mashina malumotini yangilash")
    @PreAuthorize("hasAnyRole('SCHOOL_ADMIN', 'SUPER_ADMIN', 'INSTRUCTOR')")
    @PutMapping("/{id}")
    public ResponseEntity<InstructorCarResponse> updateInstructorCar(
            @PathVariable Integer id,
            @RequestBody InstructorCarUpdateRequest request) {
        return ResponseEntity.ok(instructorCarService.updateInstructorCar(id, request));
    }

    @Operation(summary = "Mashinani uchirish")
    @PreAuthorize("hasAnyRole('SCHOOL_ADMIN', 'SUPER_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInstructorCar(@PathVariable Integer id) {
        instructorCarService.deleteInstructorCar(id);
        return ResponseEntity.noContent().build();
    }
}
