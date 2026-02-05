package org.example.autoedu.repo;

import org.example.autoedu.entity.UploadedFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UploadedFileRepository extends JpaRepository<UploadedFile, Integer> {

    // Muayyan darsdagi barcha fayllar
    List<UploadedFile> findByLessonId(Integer lessonId);

    // Faylni dars va kurs bilan birga yuklash (agar kerak bo'lsa)
    @Query("SELECT f FROM UploadedFile f JOIN FETCH f.lesson l JOIN FETCH l.course WHERE f.id = :fileId")
    Optional<UploadedFile> findWithLessonAndCourseById(@Param("fileId") Integer fileId);

    // Fayl nomiga qidirish (masalan duplicate tekshirish uchun)
    Optional<UploadedFile> findByStoredFileName(String storedFileName);
}