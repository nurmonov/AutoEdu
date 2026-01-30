package org.example.autoedu.service;


import lombok.RequiredArgsConstructor;
import org.example.autoedu.dto.course.CourseCreateRequest;
import org.example.autoedu.dto.course.CourseResponse;
import org.example.autoedu.dto.course.CourseUpdateRequest;
import org.example.autoedu.entity.Course;
import org.example.autoedu.mapper.CourseMapper;
import org.example.autoedu.repo.CourseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

    @Transactional(readOnly = true)
    public List<CourseResponse> getAllCourses() {
        return courseMapper.toResponseList(courseRepository.findAll());
    }

    @Transactional(readOnly = true)
    public CourseResponse getCourseById(Integer id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Kurs topilmadi: " + id));
        return courseMapper.toResponse(course);
    }

    @Transactional
    public CourseResponse createCourse(CourseCreateRequest request) {
        Course course = courseMapper.toEntity(request);
        Course saved = courseRepository.save(course);
        return courseMapper.toResponse(saved);
    }

    @Transactional
    public CourseResponse updateCourse(Integer id, CourseUpdateRequest request) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Kurs topilmadi: " + id));

        // Qo'lda update (mapper ishlamagan bo'lsa)
        if (request.getFullName() != null) course.setFullName(request.getFullName());
        if (request.getTitle() != null) course.setTitle(request.getTitle());
        if (request.getDescription() != null) course.setDescription(request.getDescription());
        if (request.getPrice() != null) course.setPrice(request.getPrice());
        if (request.getPhoto() != null) course.setPhoto(request.getPhoto());
        if (request.getVideoCount() != null) course.setVideoCount(request.getVideoCount());
        if (request.getLogo() != null) course.setLogo(request.getLogo());
        if (request.getLocation() != null) course.setLocation(request.getLocation());

        Course updated = courseRepository.save(course);
        return courseMapper.toResponse(updated);
    }

    @Transactional
    public void deleteCourse(Integer id) {
        if (!courseRepository.existsById(id)) {
            throw new NoSuchElementException("Kurs topilmadi: " + id);
        }
        courseRepository.deleteById(id);
    }
}
