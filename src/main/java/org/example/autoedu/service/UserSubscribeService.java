package org.example.autoedu.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.autoedu.dto.usersubscription.UserSubscribeCreateRequest;
import org.example.autoedu.dto.usersubscription.UserSubscribeResponse;
import org.example.autoedu.entity.UserSubscribe;
import org.example.autoedu.repo.UserSubscribeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
public class UserSubscribeService {

    private final UserSubscribeRepository userSubscribeRepository;

    @Transactional
    public UserSubscribeResponse subscribeUser(UserSubscribeCreateRequest request) {
        // Mavjudligini tekshirish
        if (userSubscribeRepository.findByUserIdAndSubscribeCourseId(
                request.getUserId(), request.getSubscribeCourseId()).isPresent()) {
            throw new IllegalArgumentException("Bu user allaqachon shu obuna-kursga yozilgan");
        }

        UserSubscribe us = UserSubscribe.builder()
                .userId(request.getUserId())
                .subscribeCourseId(request.getSubscribeCourseId())
                .isSubscribed(request.getIsSubscribed() != null ? request.getIsSubscribed() : true)
                .build();

        UserSubscribe saved = userSubscribeRepository.save(us);
        return toResponse(saved);
    }

    @Transactional()
    public List<UserSubscribeResponse> getSubscriptionsByUser(Integer userId) {
        return userSubscribeRepository.findByUserId(userId).stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional()
    public UserSubscribeResponse getUserSubscription(Integer userId, Integer subscribeCourseId) {
        UserSubscribe us = userSubscribeRepository.findByUserIdAndSubscribeCourseId(userId, subscribeCourseId)
                .orElseThrow(() -> new NoSuchElementException("Obuna topilmadi"));
        return toResponse(us);
    }

    @Transactional
    public void unsubscribeUser(Integer userId, Integer subscribeCourseId) {
        if (userSubscribeRepository.findByUserIdAndSubscribeCourseId(userId, subscribeCourseId).isEmpty()) {
            throw new NoSuchElementException("Obuna topilmadi");
        }
        userSubscribeRepository.deleteByUserIdAndSubscribeCourseId(userId, subscribeCourseId);
    }

    private UserSubscribeResponse toResponse(UserSubscribe entity) {
        return UserSubscribeResponse.builder()
                .id(entity.getId())
                .userId(entity.getUserId())
                .subscribeCourseId(entity.getSubscribeCourseId())
                .isSubscribed(entity.getIsSubscribed())
                .build();
    }
}