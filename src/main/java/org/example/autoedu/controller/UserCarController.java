package org.example.autoedu.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.autoedu.dto.userCar.UserCarAddRequest;
import org.example.autoedu.dto.userCar.UserCarFullResponse;
import org.example.autoedu.dto.userCar.UserCarRemoveRequest;
import org.example.autoedu.service.UserCarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user-cars")
@RequiredArgsConstructor
@Tag(name = "User Cars", description = "Uquvchi va instruktor mashinasi boglanishi")
@SecurityRequirement(name = "bearerAuth")
public class UserCarController {

    private final UserCarService userCarService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<UserCarFullResponse>> getUserCars(@PathVariable Integer userId) {
        return ResponseEntity.ok(userCarService.getUserCars(userId));
    }

    @PostMapping
    public ResponseEntity<UserCarFullResponse> addCarToUser(@RequestBody UserCarAddRequest request) {
        UserCarFullResponse response = userCarService.addCarToUser(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(summary = "Userdan mashinani uchirish")
    @PreAuthorize("hasAnyRole('SCHOOL_ADMIN', 'SUPER_ADMIN', 'INSTRUCTOR')")
    @DeleteMapping
    public ResponseEntity<Void> removeCarFromUser(@RequestBody UserCarRemoveRequest request) {
        userCarService.removeCarFromUser(request.getUserId(), request.getInstructorCarId());
        return ResponseEntity.noContent().build();
    }
}
