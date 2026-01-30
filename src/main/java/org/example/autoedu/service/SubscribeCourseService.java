package org.example.autoedu.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.autoedu.dto.subscriptionCourse.SubscribeCourseResponse;
import org.example.autoedu.entity.SubscribeCourse;
import org.example.autoedu.repo.SubscribeCourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubscribeCourseService {

    private final SubscribeCourseRepository subscribeCourseRepository;

    @Transactional
    public SubscribeCourseResponse addCourseToSubscribe(Integer subscribeId, Integer courseId) {
        SubscribeCourse sc = SubscribeCourse.builder()
                .subscribeId(subscribeId)
                .courseId(courseId)
                .build();

        SubscribeCourse saved = subscribeCourseRepository.save(sc);
        return toResponse(saved);
    }

    @Transactional()
    public List<SubscribeCourseResponse> getCoursesBySubscribe(Integer subscribeId) {
        return subscribeCourseRepository.findBySubscribeId(subscribeId).stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    private SubscribeCourseResponse toResponse(SubscribeCourse entity) {
        return SubscribeCourseResponse.builder()
                .id(entity.getId())
                .subscribeId(entity.getSubscribeId())
                .courseId(entity.getCourseId())
                .build();
    }
}
