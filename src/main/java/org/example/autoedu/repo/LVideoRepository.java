package org.example.autoedu.repo;

import org.example.autoedu.entity.LVideo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LVideoRepository extends JpaRepository<LVideo, Integer> {

    List<LVideo> findByLessonId(Integer lessonId);

    Optional<LVideo> findByLessonIdAndLessonVideoId(Integer lessonId, Integer lessonVideoId);

    void deleteByLessonIdAndLessonVideoId(Integer lessonId, Integer lessonVideoId);
}