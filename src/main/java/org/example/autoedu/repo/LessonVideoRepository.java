package org.example.autoedu.repo;


import org.example.autoedu.entity.LessonVideo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LessonVideoRepository extends JpaRepository<LessonVideo, Integer> {

    List<LessonVideo> findByLessonId(Integer lessonId);

}
