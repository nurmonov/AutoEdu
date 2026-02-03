package org.example.autoedu.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.autoedu.dto.schoolUser.SchoolUserAddRequest;
import org.example.autoedu.dto.schoolUser.SchoolUserRemoveRequest;
import org.example.autoedu.dto.schoolUser.SchoolUserResponse;
import org.example.autoedu.service.SchoolUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/school-users")
@RequiredArgsConstructor
@Tag(name = "School Users", description = "Maktab va user boglanishlari")
@SecurityRequirement(name = "bearerAuth")
public class SchoolUserController {

    private final SchoolUserService schoolUserService;

    @Operation(summary = "Maktabga user qushish (admin uchun)")
    @PreAuthorize("hasAnyRole('SCHOOL_ADMIN', 'SUPER_ADMIN')")
    @PostMapping
    public ResponseEntity<SchoolUserResponse> addUserToSchool(@RequestBody SchoolUserAddRequest request) {
        SchoolUserResponse response = schoolUserService.addUserToSchool(
                request.getSchoolId(),
                request.getUserId(),
                request.getRoleInSchool()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Maktabdagi barcha userlarni olish")
    @PreAuthorize("hasAnyRole('SCHOOL_ADMIN', 'SUPER_ADMIN')")
    @GetMapping("/school/{schoolId}")
    public ResponseEntity<List<SchoolUserResponse>> getUsersBySchool(@PathVariable Integer schoolId) {
        return ResponseEntity.ok(schoolUserService.getUsersBySchool(schoolId));
    }

    @Operation(summary = "Userning barcha maktablarini olish")
    @PreAuthorize("hasAnyRole('SCHOOL_ADMIN', 'SUPER_ADMIN')")
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<SchoolUserResponse>> getSchoolsByUser(@PathVariable Integer userId) {
        return ResponseEntity.ok(schoolUserService.getSchoolsByUser(userId));
    }

    @Operation(summary = "Maktabdan userni uchirish")
    @PreAuthorize("hasAnyRole('SCHOOL_ADMIN', 'SUPER_ADMIN')")
    @DeleteMapping
    public ResponseEntity<Void> removeUserFromSchool(@RequestBody SchoolUserRemoveRequest request) {
        schoolUserService.removeUserFromSchool(request.getSchoolId(), request.getUserId());
        return ResponseEntity.noContent().build();
    }
}