package org.example.autoedu.service;


import lombok.RequiredArgsConstructor;
import org.example.autoedu.dto.lessonVideo.LessonVideoCreateRequest;
import org.example.autoedu.dto.lessonVideo.LessonVideoResponse;
import org.example.autoedu.dto.lessonVideo.LessonVideoUpdateRequest;
import org.example.autoedu.entity.LessonVideo;
import org.example.autoedu.repo.LessonVideoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LessonVideoService {

    private final LessonVideoRepository lessonVideoRepository;

    @Transactional(readOnly = true)
    public List<LessonVideoResponse> getAllLessonVideos() {
        return lessonVideoRepository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public LessonVideoResponse getLessonVideoById(Integer id) {
        LessonVideo video = lessonVideoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Video topilmadi: " + id));
        return toResponse(video);
    }

    @Transactional(readOnly = true)
    public List<LessonVideoResponse> getVideosByLessonId(Integer lessonId) {
        return lessonVideoRepository.findByLessonId(lessonId).stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public LessonVideoResponse createLessonVideo(LessonVideoCreateRequest request) {
        LessonVideo video = LessonVideo.builder()
                .fullName(request.getFullName())
                .title(request.getTitle())
                .description(request.getDescription())
                .videoUrl(request.getVideoUrl())
                .photoUrl(request.getPhotoUrl())
          //todo      .lessonId(request.getLessonId())
                .build();

        LessonVideo saved = lessonVideoRepository.save(video);
        return toResponse(saved);
    }

    @Transactional
    public LessonVideoResponse updateLessonVideo(Integer id, LessonVideoUpdateRequest request) {
        LessonVideo video = lessonVideoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Video topilmadi: " + id));

        // Qo'lda update (null bo'lmagan maydonlarni o'zgartiramiz)
        if (request.getFullName() != null) video.setFullName(request.getFullName());
        if (request.getTitle() != null) video.setTitle(request.getTitle());
        if (request.getDescription() != null) video.setDescription(request.getDescription());
        if (request.getVideoUrl() != null) video.setVideoUrl(request.getVideoUrl());
        if (request.getPhotoUrl() != null) video.setPhotoUrl(request.getPhotoUrl());

        LessonVideo updated = lessonVideoRepository.save(video);
        return toResponse(updated);
    }

    @Transactional
    public void deleteLessonVideo(Integer id) {
        if (!lessonVideoRepository.existsById(id)) {
            throw new NoSuchElementException("Video topilmadi: " + id);
        }
        lessonVideoRepository.deleteById(id);
    }

    private LessonVideoResponse toResponse(LessonVideo entity) {
        return LessonVideoResponse.builder()
                .id(entity.getId())
                .fullName(entity.getFullName())
                .title(entity.getTitle())
                .description(entity.getDescription())
                .videoUrl(entity.getVideoUrl())
                .photoUrl(entity.getPhotoUrl())
          //todo      .lessonId(entity.getLessonId())
                .build();
    }
}
