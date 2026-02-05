//package org.example.autoedu.entity;
//
//
//import jakarta.persistence.*;
//import lombok.*;
//
//@Entity
//@Table(name = "L_videos")
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
//public class LVideo {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer id;
//
//    @Column(name = "lesson_id")
//    private Integer lessonId;
//
//    @Column(name = "lessonvideo_id")
//    private Integer lessonVideoId;
//
//    @ManyToOne
//    @JoinColumn(name = "lesson_id", insertable = false, updatable = false)
//    private Lesson lesson;
//}
