package org.example.autoedu.entity;

import jakarta.persistence.*;
import lombok.*;
@Entity
@Table(name = "lesson_videos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LessonVideo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "full_name")
    private String fullName;

    private String title;
    private String description;

    @Column(name = "video_url")
    private String videoUrl;

    @Column(name = "photo_url")
    private String photoUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lesson_id", nullable = false)
    private Lesson lesson;
}
