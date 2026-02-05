package org.example.autoedu.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "uploaded_files")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "lesson")
public class UploadedFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String originalFileName;
    private String storedFileName;
    private String contentType;
    private Long fileSize;
    private String filePath;           // diskdagi yo'l (masalan: uploads/uuid.ext)
    private LocalDateTime uploadDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lesson_id", nullable = false)  // MAJBURIY!
    private Lesson lesson;

    // Qo'shimcha yordamchi metodlar (ixtiyoriy)
    public boolean belongsToLesson(Integer lessonId) {
        return this.lesson != null && this.lesson.getId().equals(lessonId);
    }
}