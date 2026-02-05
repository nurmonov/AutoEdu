package org.example.autoedu.service;

import lombok.RequiredArgsConstructor;
import org.example.autoedu.dto.lesson.LessonCreateRequest;
import org.example.autoedu.dto.lesson.LessonResponse;
import org.example.autoedu.dto.lesson.LessonUpdateRequest;
import org.example.autoedu.entity.Course;
import org.example.autoedu.entity.Lesson;
import org.example.autoedu.mapper.LessonMapper;
import org.example.autoedu.repo.CourseRepository;
import org.example.autoedu.repo.LessonRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class LessonService {

    private final LessonRepository lessonRepository;
    private final CourseRepository courseRepository;
    private final LessonMapper lessonMapper;

    @Transactional(readOnly = true)
    public List<LessonResponse> getAllLessons() {
        return lessonMapper.toResponseList(lessonRepository.findAll());
    }

    @Transactional(readOnly = true)
    public LessonResponse getLessonById(Integer id) {
        Lesson lesson = lessonRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Dars topilmadi: " + id));
        return lessonMapper.toResponse(lesson);
    }

    // Fayllar bilan birga olish uchun qo'shimcha metod (ixtiyoriy, controllerdan chaqirish mumkin)
    @Transactional(readOnly = true)
    public LessonResponse getLessonWithFiles(Integer id) {
        Lesson lesson = lessonRepository.findWithFilesById(id)
                .orElseThrow(() -> new NoSuchElementException("Dars topilmadi: " + id));
        return lessonMapper.toResponse(lesson);
    }

    @Transactional
    public LessonResponse createLesson(LessonCreateRequest request) {
        Course course = courseRepository.findById(request.getCourseId())
                .orElseThrow(() -> new NoSuchElementException("Kurs topilmadi: " + request.getCourseId()));

        Lesson lesson = lessonMapper.toEntity(request);
        lesson.setCourse(course);  // majburiy bog'lash

        Lesson saved = lessonRepository.save(lesson);
        return lessonMapper.toResponse(saved);
    }

    @Transactional
    public LessonResponse updateLesson(Integer id, LessonUpdateRequest request) {
        Lesson lesson = lessonRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Dars topilmadi: " + id));

        // course ni o'zgartirmaymiz (agar kerak bo'lsa alohida metod qilish mumkin)
        if (request.getFullName() != null) lesson.setFullName(request.getFullName());
        if (request.getTitle() != null) lesson.setTitle(request.getTitle());
        if (request.getDescription() != null) lesson.setDescription(request.getDescription());
        if (request.getFile() != null) lesson.setFile(request.getFile());

        Lesson updated = lessonRepository.save(lesson);
        return lessonMapper.toResponse(updated);
    }

    @Transactional
    public void deleteLesson(Integer id) {
        if (!lessonRepository.existsById(id)) {
            throw new NoSuchElementException("Dars topilmadi: " + id);
        }
        // cascade bo'lgani uchun uploadedFiles avto o'chiriladi
        lessonRepository.deleteById(id);
    }
}