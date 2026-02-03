package org.example.autoedu.service;


import lombok.RequiredArgsConstructor;
import org.example.autoedu.dto.lesson.LessonCreateRequest;
import org.example.autoedu.dto.lesson.LessonResponse;
import org.example.autoedu.dto.lesson.LessonUpdateRequest;
import org.example.autoedu.entity.Lesson;
import org.example.autoedu.mapper.LessonMapper;
import org.example.autoedu.repo.LessonRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class LessonService {

    private final LessonRepository lessonRepository;
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

    @Transactional
    public LessonResponse createLesson(LessonCreateRequest request) {
        Lesson lesson = lessonMapper.toEntity(request);
        Lesson saved = lessonRepository.save(lesson);
        return lessonMapper.toResponse(saved);
    }

    @Transactional
    public LessonResponse updateLesson(Integer id, LessonUpdateRequest request) {
        Lesson lesson = lessonRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Dars topilmadi: " + id));


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
        lessonRepository.deleteById(id);
    }
}
