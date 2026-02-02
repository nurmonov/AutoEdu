package org.example.autoedu.service;

import lombok.RequiredArgsConstructor;
import org.example.autoedu.dto.course.CourseResponse;
import org.example.autoedu.dto.subscription.SubscribeResponse;
import org.example.autoedu.dto.subscriptionCourse.SubscribeCourseResponse;
import org.example.autoedu.dto.user.UserResponse;
import org.example.autoedu.dto.usersubscription.UserSubscribeCreateRequest;
import org.example.autoedu.dto.usersubscription.UserSubscribeResponse;
import org.example.autoedu.entity.*;
import org.example.autoedu.mapper.UserSubscribeMapper;
import org.example.autoedu.repo.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class UserSubscribeService {

    private final UserSubscribeRepository userSubscribeRepository;
    private final UserRepository userRepository;
    private final SubscribeCourseRepository subscribeCourseRepository;
    private final SubscribeRepository subscribeRepository;
    private final CourseRepository courseRepository;
    private final UserSubscribeMapper userSubscribeMapper; // sizdagi mapper

    @Transactional
    public UserSubscribeResponse subscribeUser(UserSubscribeCreateRequest request) {
        if (userSubscribeRepository.findByUserIdAndSubscribeCourseId(
                request.getUserId(), request.getSubscribeCourseId()).isPresent()) {
            throw new IllegalArgumentException("Bu user allaqachon shu obuna-kursga yozilgan");
        }

        UserSubscribe us = userSubscribeMapper.toEntity(request);
        UserSubscribe saved = userSubscribeRepository.save(us);
        return toFullResponse(saved);
    }

    @Transactional(readOnly = true)
    public List<UserSubscribeResponse> getSubscriptionsByUser(Integer userId) {
        return userSubscribeRepository.findByUserId(userId).stream()
                .map(this::toFullResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public UserSubscribeResponse getUserSubscription(Integer userId, Integer subscribeCourseId) {
        UserSubscribe us = userSubscribeRepository.findByUserIdAndSubscribeCourseId(userId, subscribeCourseId)
                .orElseThrow(() -> new NoSuchElementException("Obuna topilmadi"));

        return toFullResponse(us);
    }

    @Transactional
    public void unsubscribeUser(Integer userId, Integer subscribeCourseId) {
        UserSubscribe us = userSubscribeRepository.findByUserIdAndSubscribeCourseId(userId, subscribeCourseId)
                .orElseThrow(() -> new NoSuchElementException("Obuna topilmadi"));

        userSubscribeRepository.delete(us);
    }

    // Mapperdan oddiy mapping + qo'shimcha obyektlar
    private UserSubscribeResponse toFullResponse(UserSubscribe entity) {
        UserSubscribeResponse base = userSubscribeMapper.toResponse(entity);

        // User ma'lumotlarini qo'shamiz
        User user = userRepository.findById(entity.getUserId())
                .orElseThrow(() -> new NoSuchElementException("User topilmadi"));

        UserResponse userDto = UserResponse.builder()
                .id(user.getId())
                .fullName(user.getFullName())
                .phoneNumber(user.getPhoneNumber())
                .email(user.getEmail())
                .role(user.getRole() != null ? user.getRole().name() : null)
                .photo(user.getPhoto())
                .build();

        // SubscribeCourse ma'lumotlarini qo'shamiz
        SubscribeCourse sc = subscribeCourseRepository.findById(entity.getSubscribeCourseId())
                .orElseThrow(() -> new NoSuchElementException("SubscribeCourse topilmadi"));

        Subscribe subscribe = subscribeRepository.findById(sc.getSubscribeId())
                .orElseThrow(() -> new NoSuchElementException("Subscribe topilmadi"));

        Course course = courseRepository.findById(sc.getCourseId())
                .orElseThrow(() -> new NoSuchElementException("Course topilmadi"));

        SubscribeCourseResponse scDto = SubscribeCourseResponse.builder()
                .id(sc.getId())
                .subscribe(SubscribeResponse.builder()
                        .id(subscribe.getId())
                        .fullName(subscribe.getFullName())
                        .price(subscribe.getPrice())
                        .additionals(subscribe.getAdditionals())
                        .sale(subscribe.getSale())
                        .promo(subscribe.getPromo())
                        .promoId(subscribe.getPromoId())
                        .build())
                .course(CourseResponse.builder()
                        .id(course.getId())
                        .title(course.getTitle())
                        .description(course.getDescription())
                        .price(course.getPrice())
                        .photo(course.getPhoto())
                        .location(course.getLocation())
                        .build())
                .build();

        return UserSubscribeResponse.builder()
                .id(base.getId())
                .user(userDto)
                .subscribeCourse(scDto)
                .isSubscribed(base.getIsSubscribed())
                .build();
    }
}