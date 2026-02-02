package org.example.autoedu.service;

import lombok.RequiredArgsConstructor;
import org.example.autoedu.dto.course.CourseResponse;
import org.example.autoedu.dto.subscription.SubscribeResponse;
import org.example.autoedu.dto.subscriptionCourse.SubscribeCourseResponse;
import org.example.autoedu.entity.Course;
import org.example.autoedu.entity.Subscribe;
import org.example.autoedu.entity.SubscribeCourse;
import org.example.autoedu.repo.CourseRepository;
import org.example.autoedu.repo.SubscribeCourseRepository;
import org.example.autoedu.repo.SubscribeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
public class SubscribeCourseService {

    private final SubscribeCourseRepository subscribeCourseRepository;
    private final SubscribeRepository subscribeRepository;  // qo'shing
    private final CourseRepository courseRepository;        // qo'shing

    @Transactional
    public SubscribeCourseResponse addCourseToSubscribe(Integer subscribeId, Integer courseId) {
        SubscribeCourse sc = SubscribeCourse.builder()
                .subscribeId(subscribeId)
                .courseId(courseId)
                .build();

        SubscribeCourse saved = subscribeCourseRepository.save(sc);
        return toResponse(saved);
    }

    @Transactional(readOnly = true)
    public List<SubscribeCourseResponse> getCoursesBySubscribe(Integer subscribeId) {
        List<SubscribeCourse> courses = subscribeCourseRepository.findBySubscribeId(subscribeId);
        return courses.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    private SubscribeCourseResponse toResponse(SubscribeCourse entity) {
        Subscribe subscribe = subscribeRepository.findById(entity.getSubscribeId())
                .orElseThrow(() -> new NoSuchElementException("Subscribe topilmadi: " + entity.getSubscribeId()));

        Course course = courseRepository.findById(entity.getCourseId())
                .orElseThrow(() -> new NoSuchElementException("Course topilmadi: " + entity.getCourseId()));

        return SubscribeCourseResponse.builder()
                .id(entity.getId())
                .subscribe(SubscribeResponse.builder()
                        .id(subscribe.getId())
                        .fullName(subscribe.getFullName())
                        .price(subscribe.getPrice())
                        // boshqa subscribe maydonlarini qo'shing
                        .build())
                .course(CourseResponse.builder()
                        .id(course.getId())
                        .title(course.getTitle())
                        .description(course.getDescription())
                        // boshqa course maydonlarini qo'shing
                        .build())
                .build();
    }
}