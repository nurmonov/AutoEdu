package org.example.autoedu.service;


import lombok.RequiredArgsConstructor;
import org.example.autoedu.dto.lVideo.LVideoAddRequest;
import org.example.autoedu.dto.lVideo.LVideoResponse;
import org.example.autoedu.dto.lVideo.LessonVideoFullResponse;
import org.example.autoedu.entity.LVideo;
import org.example.autoedu.entity.LessonVideo;
import org.example.autoedu.mapper.LessonVideoMapper;
import org.example.autoedu.repo.LVideoRepository;
import org.example.autoedu.repo.LessonVideoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LVideoService {

    private final LVideoRepository lVideoRepository;
    private final LessonVideoRepository lessonVideoRepository; // qo'shimcha repository kerak (lesson_videos uchun)
    private final LessonVideoMapper lessonVideoMapper; // sizdagi video mapper (agar bor bo'lsa)

    @Transactional(readOnly = true)
    public List<LessonVideoFullResponse> getVideosByLesson(Integer lessonId) {
        // Darsga bog'langan barcha LVideo'larni topamiz
        List<LVideo> lVideos = lVideoRepository.findByLessonId(lessonId);

        // Har bir LVideo uchun tegishli LessonVideo ni olamiz va DTO qaytaramiz
        return lVideos.stream()
                .map(lVideo -> {
                    // lessonVideoId orqali to'liq video ma'lumotini olish
                    LessonVideo lessonVideo = lessonVideoRepository.findById(lVideo.getLessonVideoId())
                            .orElseThrow(() -> new NoSuchElementException("Video topilmadi: " + lVideo.getLessonVideoId()));

                    // Mapperdan foydalanib DTO yaratamiz (sizda bor mapper)
                    LessonVideoFullResponse response = lessonVideoMapper.toFullResponse(lessonVideo);

                    // Agar mapperda mapping yo'q bo'lsa — qo'lda qilish mumkin
                    // response = LessonVideoFullResponse.builder()
                    //         .id(lessonVideo.getId())
                    //         .fullName(lessonVideo.getFullName())
                    //         .title(lessonVideo.getTitle())
                    //         .description(lessonVideo.getDescription())
                    //         .videoUrl(lessonVideo.getVideoUrl())
                    //         .photoUrl(lessonVideo.getPhotoUrl())
                    //         .lessonId(lessonVideo.getLessonId())
                    //         .build();

                    return response;
                })
                .collect(Collectors.toList());
    }

    @Transactional
    public LessonVideoFullResponse addVideoToLesson(LVideoAddRequest request) {
        // Mavjudligini tekshirish
        if (lVideoRepository.findByLessonIdAndLessonVideoId(
                request.getLessonId(), request.getLessonVideoId()).isPresent()) {
            throw new IllegalArgumentException("Bu video allaqachon shu darsga qo'shilgan");
        }

        LVideo lVideo = LVideo.builder()
                .lessonId(request.getLessonId())
                .lessonVideoId(request.getLessonVideoId())
                .build();

        LVideo saved = lVideoRepository.save(lVideo);

        // Qo'shilgan videoning to'liq ma'lumotini olish
        LessonVideo lessonVideo = lessonVideoRepository.findById(saved.getLessonVideoId())
                .orElseThrow(() -> new NoSuchElementException("Video topilmadi"));

        return lessonVideoMapper.toFullResponse(lessonVideo);
        // yoki qo'lda:
        // return LessonVideoFullResponse.builder()
        //         .id(lessonVideo.getId())
        //         .fullName(lessonVideo.getFullName())
        //         .title(lessonVideo.getTitle())
        //         .description(lessonVideo.getDescription())
        //         .videoUrl(lessonVideo.getVideoUrl())
        //         .photoUrl(lessonVideo.getPhotoUrl())
        //         .lessonId(lessonVideo.getLessonId())
        //         .build();
    }

    @Transactional
    public void removeVideoFromLesson(Integer lessonId, Integer lessonVideoId) {
        if (lVideoRepository.findByLessonIdAndLessonVideoId(lessonId, lessonVideoId).isEmpty()) {
            throw new NoSuchElementException("Bog'lanish topilmadi");
        }

        lVideoRepository.deleteByLessonIdAndLessonVideoId(lessonId, lessonVideoId);
    }
}