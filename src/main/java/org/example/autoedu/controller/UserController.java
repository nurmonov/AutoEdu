package org.example.autoedu.controller;



import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.autoedu.dto.user.UserCreateRequest;
import org.example.autoedu.dto.user.UserResponse;
import org.example.autoedu.dto.user.UserUpdateRequest;
import org.example.autoedu.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Tag(name = "Users", description = "Foydalanuvchilar bilan ishlash (admin uchun)")
@SecurityRequirement(name = "bearerAuth")  // Swaggerda token talab qiladi
public class UserController {

    private final UserService userService;

    @Operation(summary = "Barcha foydalanuvchilarni olish", description = "Faqat adminlar uchun")
    @PreAuthorize("hasAnyRole('SCHOOL_ADMIN', 'SUPER_ADMIN')")
    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @Operation(summary = "ID bo'yicha foydalanuvchi ma'lumotini olish")
    @PreAuthorize("hasAnyRole('SCHOOL_ADMIN', 'SUPER_ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Integer id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @Operation(summary = "Yangi foydalanuvchi qo'shish (admin tomonidan)")
    @PreAuthorize("hasAnyRole('SCHOOL_ADMIN', 'SUPER_ADMIN')")
    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody UserCreateRequest request) {
        UserResponse response = userService.createUser(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(summary = "Foydalanuvchi ma'lumotini yangilash")
    @PreAuthorize("hasAnyRole('SCHOOL_ADMIN', 'SUPER_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(
            @PathVariable Integer id,
            @RequestBody UserUpdateRequest request) {
        return ResponseEntity.ok(userService.updateUser(id, request));
    }

    @Operation(summary = "Foydalanuvchini o'chirish")
    @PreAuthorize("hasAnyRole('SCHOOL_ADMIN', 'SUPER_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Telefon raqami bo'yicha foydalanuvchi qidirish")
    @PreAuthorize("hasAnyRole('SCHOOL_ADMIN', 'SUPER_ADMIN')")
    @GetMapping("/phone/{phoneNumber}")
    public ResponseEntity<UserResponse> getUserByPhoneNumber(@PathVariable String phoneNumber) {
        return ResponseEntity.ok(userService.getUserByPhoneNumber(phoneNumber));
    }
}
