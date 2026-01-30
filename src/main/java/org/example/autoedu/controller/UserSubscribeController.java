package org.example.autoedu.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.autoedu.dto.usersubscription.UserSubscribeCreateRequest;
import org.example.autoedu.dto.usersubscription.UserSubscribeResponse;
import org.example.autoedu.dto.usersubscription.UserSubscribeUnsubscribeRequest;
import org.example.autoedu.service.UserSubscribeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user-subscribes")
@RequiredArgsConstructor
@Tag(name = "User Subscriptions", description = "Foydalanuvchining obunalari bilan ishlash")
@SecurityRequirement(name = "bearerAuth")
public class UserSubscribeController {

    private final UserSubscribeService userSubscribeService;

    @Operation(summary = "Userni obuna qilish")
    @PostMapping
    public ResponseEntity<UserSubscribeResponse> subscribeUser(@RequestBody UserSubscribeCreateRequest request) {
        UserSubscribeResponse response = userSubscribeService.subscribeUser(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(summary = "Userning barcha obunalarini olish")
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<UserSubscribeResponse>> getSubscriptionsByUser(@PathVariable Integer userId) {
        return ResponseEntity.ok(userSubscribeService.getSubscriptionsByUser(userId));
    }

    @Operation(summary = "Userning ma'lum bir obunasini olish")
    @GetMapping("/user/{userId}/subscribe-course/{subscribeCourseId}")
    public ResponseEntity<UserSubscribeResponse> getUserSubscription(
            @PathVariable Integer userId,
            @PathVariable Integer subscribeCourseId) {
        return ResponseEntity.ok(userSubscribeService.getUserSubscription(userId, subscribeCourseId));
    }

    @Operation(summary = "Userni obunadan chiqarish")
    @PreAuthorize("hasAnyRole('SCHOOL_ADMIN', 'SUPER_ADMIN')")
    @DeleteMapping
    public ResponseEntity<Void> unsubscribeUser(@RequestBody UserSubscribeUnsubscribeRequest request) {
        userSubscribeService.unsubscribeUser(request.getUserId(), request.getSubscribeCourseId());
        return ResponseEntity.noContent().build();
    }
}
