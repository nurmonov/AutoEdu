package org.example.autoedu.service;


import lombok.RequiredArgsConstructor;
import org.example.autoedu.dto.lVideo.LVideoAddRequest;
import org.example.autoedu.dto.lVideo.LVideoResponse;
import org.example.autoedu.entity.LVideo;
import org.example.autoedu.repo.LVideoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LVideoService {

    private final LVideoRepository lVideoRepository;

    @Transactional(readOnly = true)
    public List<LVideoResponse> getVideosByLesson(Integer lessonId) {
        return lVideoRepository.findByLessonId(lessonId).stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public LVideoResponse addVideoToLesson(LVideoAddRequest request) {
        // Mavjudligini tekshirish
        if (lVideoRepository.findByLessonIdAndLessonVideoId(request.getLessonId(), request.getLessonVideoId()).isPresent()) {
            throw new IllegalArgumentException("Bu video allaqachon shu darsga qo'shilgan");
        }

        LVideo lVideo = LVideo.builder()
                .lessonId(request.getLessonId())
                .lessonVideoId(request.getLessonVideoId())
                .build();

        LVideo saved = lVideoRepository.save(lVideo);
        return toResponse(saved);
    }

    @Transactional
    public void removeVideoFromLesson(Integer lessonId, Integer lessonVideoId) {
        if (lVideoRepository.findByLessonIdAndLessonVideoId(lessonId, lessonVideoId).isEmpty()) {
            throw new NoSuchElementException("Bog'lanish topilmadi");
        }

        lVideoRepository.deleteByLessonIdAndLessonVideoId(lessonId, lessonVideoId);
    }

    private LVideoResponse toResponse(LVideo entity) {
        return LVideoResponse.builder()
                .id(entity.getId())
                .lessonId(entity.getLessonId())
                .lessonVideoId(entity.getLessonVideoId())
                .build();
    }
}
