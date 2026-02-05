package org.example.autoedu.repo;

import org.example.autoedu.entity.Lesson;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Integer> {

    // Darsni va uning fayllarini birga yuklash
    @EntityGraph(attributePaths = {"uploadedFiles"})
    Optional<Lesson> findWithFilesById(Integer id);

    // Kursga tegishli barcha darslarni olish
    List<Lesson> findByCourseId(Integer courseId);

    // Dars va uning kursini birga yuklash (agar kerak bo'lsa)
    @Query("SELECT l FROM Lesson l JOIN FETCH l.course WHERE l.id = :id")
    Optional<Lesson> findWithCourseById(@Param("id") Integer id);
}
