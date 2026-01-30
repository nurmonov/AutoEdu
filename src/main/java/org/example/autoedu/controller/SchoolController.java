package org.example.autoedu.controller;



import lombok.RequiredArgsConstructor;
import org.example.autoedu.dto.school.SchoolCreateRequest;
import org.example.autoedu.dto.school.SchoolResponse;
import org.example.autoedu.dto.school.SchoolUpdateRequest;
import org.example.autoedu.service.SchoolService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schools")
@RequiredArgsConstructor
public class SchoolController {

    private final SchoolService schoolService;

    @GetMapping
    public ResponseEntity<List<SchoolResponse>> getAllSchools() {
        return ResponseEntity.ok(schoolService.getAllSchools());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SchoolResponse> getSchoolById(@PathVariable Integer id) {
        return ResponseEntity.ok(schoolService.getSchoolById(id));
    }

    @PostMapping
    public ResponseEntity<SchoolResponse> createSchool(@RequestBody SchoolCreateRequest request) {
        SchoolResponse response = schoolService.createSchool(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SchoolResponse> updateSchool(
            @PathVariable Integer id,
            @RequestBody SchoolUpdateRequest request) {
        return ResponseEntity.ok(schoolService.updateSchool(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchool(@PathVariable Integer id) {
        schoolService.deleteSchool(id);
        return ResponseEntity.noContent().build();
    }

    // qo'shimcha endpoint misoli
    @GetMapping("/active")
    public ResponseEntity<List<SchoolResponse>> getActiveSchools() {
        return ResponseEntity.ok(schoolService.findActiveSchools());
    }
}
