//package org.example.autoedu.config;
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.example.autoedu.entity.*;
//import org.example.autoedu.entity.composite.SchoolUserId;
//import org.example.autoedu.entity.enums.*;
//import org.example.autoedu.repo.*;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import java.math.BigDecimal;
//import java.time.LocalDateTime;
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@Configuration
//@RequiredArgsConstructor
//@Slf4j
//public class Dataloader {
//
//    private final PasswordEncoder passwordEncoder;
//
//    @Bean
//    CommandLineRunner initDatabase(
//            UserRepository userRepository,
//            CourseRepository courseRepository,
//            LessonRepository lessonRepository,
//            LessonVideoRepository lessonVideoRepository,
//            InstructorCarRepository instructorCarRepository,
//            SubscribeRepository subscribeRepository,
//            SubscribeCourseRepository subscribeCourseRepository,
//       //     PaymentRepository paymentRepository,
//            SchoolRepository schoolRepository,
//            SchoolUserRepository schoolUserRepository,
//            UserCarRepository userCarRepository,
//            UserSubscribeRepository userSubscribeRepository,
//            LVideoRepository lVideoRepository,
//            UploadedFileRepository uploadedFileRepository) {
//
//        return args -> {
//            log.info("Dataloader ishga tushmoqda...");
//
//            // User ma'lumotlari (2 tasi ADMIN)
//            if (userRepository.count() == 0) {
//                List<User> users = Arrays.asList(
//                        User.builder()
//                                .fullName("Admin User 1")
//                                .phoneNumber("+998901234567")
//                                .email("admin1@example.com")
//                                .password(passwordEncoder.encode("admin123"))
//                                .photo("admin1.jpg")
//                                .jshshr("12345678901234")
//                                .seriaRaqam("AB1234567")
//                                .passportPhoto("passport1.jpg")
//                                .priceHour(50000)
//                                .freeHours(2)
//                                .role(Role.ADMIN)
//                                .status(Status.ACTIVE)
//                                .build(),
//
//                        User.builder()
//                                .fullName("Admin User 2")
//                                .phoneNumber("+998901234568")
//                                .email("admin2@example.com")
//                                .password(passwordEncoder.encode("admin123"))
//                                .photo("admin2.jpg")
//                                .jshshr("23456789012345")
//                                .seriaRaqam("CD2345678")
//                                .passportPhoto("passport2.jpg")
//                                .priceHour(45000)
//                                .freeHours(3)
//                                .role(Role.ADMIN)
//                                .status(Status.ACTIVE)
//                                .build(),
//
//                        User.builder()
//                                .fullName("Student User 1")
//                                .phoneNumber("+998901234569")
//                                .email("student1@example.com")
//                                .password(passwordEncoder.encode("student123"))
//                                .photo("student1.jpg")
//                                .jshshr("34567890123456")
//                                .seriaRaqam("EF3456789")
//                                .passportPhoto("passport3.jpg")
//                                .priceHour(null)
//                                .freeHours(0)
//                                .role(Role.STUDENT)
//                                .status(Status.ACTIVE)
//                                .build(),
//
//                        User.builder()
//                                .fullName("Instructor User 1")
//                                .phoneNumber("+998901234570")
//                                .email("instructor1@example.com")
//                                .password(passwordEncoder.encode("instructor123"))
//                                .photo("instructor1.jpg")
//                                .jshshr("45678901234567")
//                                .seriaRaqam("GH4567890")
//                                .passportPhoto("passport4.jpg")
//                                .priceHour(60000)
//                                .freeHours(5)
//                                .role(Role.INSTRUCTOR)
//                                .status(Status.ACTIVE)
//                                .build(),
//
//                        User.builder()
//                                .fullName("Instructor User 2")
//                                .phoneNumber("+998901234571")
//                                .email("instructor2@example.com")
//                                .password(passwordEncoder.encode("instructor123"))
//                                .photo("instructor2.jpg")
//                                .jshshr("56789012345678")
//                                .seriaRaqam("IJ5678901")
//                                .passportPhoto("passport5.jpg")
//                                .priceHour(55000)
//                                .freeHours(4)
//                                .role(Role.INSTRUCTOR)
//                                .status(Status.INACTIVE)
//                                .build()
//                );
//
//                userRepository.saveAll(users);
//                log.info("5 ta user yaratildi (2 tasi ADMIN)");
//            }
//
//            // Course ma'lumotlari
//            if (courseRepository.count() == 0) {
//                List<Course> courses = Arrays.asList(
//                        Course.builder()
//                                .fullName("Avtomobil Haydash Asoslari")
//                                .title("Birinchi kurs")
//                                .description("Avtomobil haydashning asosiy qoidalari va texnikalari")
//                                .price(new BigDecimal("1500000.00"))
//                                .photo("course1.jpg")
//                                .videoCount(15)
//                                .logo("logo1.png")
//                                .location("Toshkent sh., Yunusobod")
//                                .build(),
//
//                        Course.builder()
//                                .fullName("Murakkab Sharoitlarda Haydash")
//                                .title("Ikkinchi kurs")
//                                .description("Yomg'ir, qor va tungi haydash texnikalari")
//                                .price(new BigDecimal("2000000.00"))
//                                .photo("course2.jpg")
//                                .videoCount(20)
//                                .logo("logo2.png")
//                                .location("Toshkent sh., Chilonzor")
//                                .build(),
//
//                        Course.builder()
//                                .fullName("Ekspress Haydash Kursi")
//                                .title("Tezkor kurs")
//                                .description("Qisqa muddatda haydashni o'rganish")
//                                .price(new BigDecimal("1200000.00"))
//                                .photo("course3.jpg")
//                                .videoCount(10)
//                                .logo("logo3.png")
//                                .location("Toshkent sh., Mirzo Ulug'bek")
//                                .build(),
//
//                        Course.builder()
//                                .fullName("Yuk Avtomobili Haydash")
//                                .title("C kategorya")
//                                .description("Yuk avtomobillarini haydash kursi")
//                                .price(new BigDecimal("2500000.00"))
//                                .photo("course4.jpg")
//                                .videoCount(25)
//                                .logo("logo4.png")
//                                .location("Toshkent sh., Sergeli")
//                                .build(),
//
//                        Course.builder()
//                                .fullName("Avtobus Haydash")
//                                .title("D kategorya")
//                                .description("Avtobus va mikroavtobuslarni haydash kursi")
//                                .price(new BigDecimal("3000000.00"))
//                                .photo("course5.jpg")
//                                .videoCount(30)
//                                .logo("logo5.png")
//                                .location("Toshkent sh., Yashnobod")
//                                .build()
//                );
//
//                courseRepository.saveAll(courses);
//                log.info("5 ta course yaratildi");
//            }
//
//            // Lesson ma'lumotlari
//            if (lessonRepository.count() == 0) {
//                List<Lesson> lessons = Arrays.asList(
//                        Lesson.builder()
//                                .fullName("Dars 1: Avtomobil tuzilishi")
//                                .title("Kirish")
//                                .description("Avtomobilning asosiy qismlari va ularning vazifalari")
//                                .file("lesson1.pdf")
//                                .build(),
//
//                        Lesson.builder()
//                                .fullName("Dars 2: Dvigatel ishlashi")
//                                .title("Dvigatel")
//                                .description("Ichki yonuv dvigatelining ishlash printsipi")
//                                .file("lesson2.pdf")
//                                .build(),
//
//                        Lesson.builder()
//                                .fullName("Dars 3: Transmissiya")
//                                .title("Uzatmalar qutisi")
//                                .description("Mexanik va avtomatik uzatmalar qutilari")
//                                .file("lesson3.pdf")
//                                .build(),
//
//                        Lesson.builder()
//                                .fullName("Dars 4: Tormoz tizimi")
//                                .title("Xavfsizlik")
//                                .description("Tormoz tizimining turlari va ishlashi")
//                                .file("lesson4.pdf")
//                                .build(),
//
//                        Lesson.builder()
//                                .fullName("Dars 5: Rul boshqaruvi")
//                                .title("Haydash")
//                                .description("Rul boshqaruvi va stabilizatsiya tizimlari")
//                                .file("lesson5.pdf")
//                                .build()
//                );
//
//                lessonRepository.saveAll(lessons);
//                log.info("5 ta lesson yaratildi");
//            }
//
//            // InstructorCar ma'lumotlari
//            if (instructorCarRepository.count() == 0) {
//                List<InstructorCar> instructorCars = Arrays.asList(
//                        InstructorCar.builder()
//                                .fullName("Chevrolet Cobalt")
//                                .photo("cobalt.jpg")
//                                .number("01A777AA")
//                                .build(),
//
//                        InstructorCar.builder()
//                                .fullName("Nissan Sunny")
//                                .photo("sunny.jpg")
//                                .number("01B888BB")
//                                .build(),
//
//                        InstructorCar.builder()
//                                .fullName("Toyota Camry")
//                                .photo("camry.jpg")
//                                .number("01C999CC")
//                                .build(),
//
//                        InstructorCar.builder()
//                                .fullName("Hyundai Accent")
//                                .photo("accent.jpg")
//                                .number("01D111DD")
//                                .build(),
//
//                        InstructorCar.builder()
//                                .fullName("KIA Rio")
//                                .photo("rio.jpg")
//                                .number("01E222EE")
//                                .build()
//                );
//
//                instructorCarRepository.saveAll(instructorCars);
//                log.info("5 ta instructor car yaratildi");
//            }
//
//            // Subscribe ma'lumotlari
//            if (subscribeRepository.count() == 0) {
//                List<Subscribe> subscribes = Arrays.asList(
//                        Subscribe.builder()
//                                .fullName("Oylik obuna")
//                                .price(new BigDecimal("500000.00"))
//                                .additionals("Barcha kurslar, 24/7 qo'llab-quvvatlash")
//                                .subscribeData(SubscribeData.MONTHLY)
//                                .sale("10%")
//                                .promo("MONTH10")
//                                .promoId(1)
//                                .build(),
//
//                        Subscribe.builder()
//                                .fullName("Yillik obuna")
//                                .price(new BigDecimal("5000000.00"))
//                                .additionals("Barcha kurslar, shaxsiy murabbiy, sertifikat")
//                                .subscribeData(SubscribeData.YEARLY)
//                                .sale("20%")
//                                .promo("YEAR20")
//                                .promoId(2)
//                                .build(),
//
//                        Subscribe.builder()
//                                .fullName("Kvartal obuna")
//                                .price(new BigDecimal("1500000.00"))
//                                .additionals("Barcha kurslar, testlar")
//                                .subscribeData(SubscribeData.QUARTERLY)
//                                .sale("15%")
//                                .promo("QRT15")
//                                .promoId(3)
//                                .build(),
//
//                        Subscribe.builder()
//                                .fullName("Yarim yillik obuna")
//                                .price(new BigDecimal("3000000.00"))
//                                .additionals("Barcha kurslar, video materiallar")
//                                .subscribeData(SubscribeData.HALF_YEARLY)
//                                .sale("18%")
//                                .promo("HALF18")
//                                .promoId(4)
//                                .build(),
//
//                        Subscribe.builder()
//                                .fullName("Haftalik obuna")
//                                .price(new BigDecimal("150000.00"))
//                                .additionals("Asosiy kurslar")
//                                .subscribeData(SubscribeData.WEEKLY)
//                                .sale("5%")
//                                .promo("WEEK5")
//                                .promoId(5)
//                                .build()
//                );
//
//                subscribeRepository.saveAll(subscribes);
//                log.info("5 ta subscribe yaratildi");
//            }
//
//            // School ma'lumotlari
//            if (schoolRepository.count() == 0) {
//                List<School> schools = Arrays.asList(
//                        School.builder()
//                                .fullName("Avto Haydash Maktabi 1")
//                                .status(Status.ACTIVE)
//                                .phoneNumber("+998711234567")
//                                .logo("school1.jpg")
//                                .location("Toshkent, Yunusobod 12")
//                                .build(),
//
//                        School.builder()
//                                .fullName("Avto Haydash Maktabi 2")
//                                .status(Status.ACTIVE)
//                                .phoneNumber("+998711234568")
//                                .logo("school2.jpg")
//                                .location("Toshkent, Chilonzor 23")
//                                .build(),
//
//                        School.builder()
//                                .fullName("Avto Haydash Maktabi 3")
//                                .status(Status.ACTIVE)
//                                .phoneNumber("+998711234569")
//                                .logo("school3.jpg")
//                                .location("Toshkent, Mirzo Ulug'bek 34")
//                                .build(),
//
//                        School.builder()
//                                .fullName("Avto Haydash Maktabi 4")
//                                .status(Status.INACTIVE)
//                                .phoneNumber("+998711234570")
//                                .logo("school4.jpg")
//                                .location("Toshkent, Sergeli 45")
//                                .build(),
//
//                        School.builder()
//                                .fullName("Avto Haydash Maktabi 5")
//                                .status(Status.ACTIVE)
//                                .phoneNumber("+998711234571")
//                                .logo("school5.jpg")
//                                .location("Toshkent, Yashnobod 56")
//                                .build()
//                );
//
//                schoolRepository.saveAll(schools);
//                log.info("5 ta school yaratildi");
//            }
//
////            // Payment ma'lumotlari (JSONB uchun Map ishlatamiz)
////            if (paymentRepository.count() == 0) {
////                // Birinchi payment uchun JSON data
////                Map<String, Object> paymentData1 = new HashMap<>();
////                paymentData1.put("course_id", 1);
////                paymentData1.put("payment_method", "click");
////                paymentData1.put("transaction_id", "txn_123456");
////                paymentData1.put("timestamp", "2024-01-30T10:30:00Z");
////
////                // Ikkinchi payment uchun JSON data
////                Map<String, Object> paymentData2 = new HashMap<>();
////                paymentData2.put("subscription", "monthly");
////                paymentData2.put("payment_method", "payme");
////                paymentData2.put("user_email", "student1@example.com");
////                paymentData2.put("amount_uzs", 500000);
////
////                // Uchinchi payment uchun JSON data
////                Map<String, Object> paymentData3 = new HashMap<>();
////                paymentData3.put("course_id", 2);
////                paymentData3.put("payment_method", "card");
////                paymentData3.put("card_last_4", "4242");
////                paymentData3.put("currency", "UZS");
////
////                // To'rtinchi payment uchun JSON data
////                Map<String, Object> paymentData4 = new HashMap<>();
////                paymentData4.put("course_id", 5);
////                paymentData4.put("payment_method", "cash");
////                paymentData4.put("branch", "Toshkent sh., Yunusobod");
////                paymentData4.put("receipt_number", "RC-2024-001");
////
////                // Beshinchi payment uchun JSON data
////                Map<String, Object> paymentData5 = new HashMap<>();
////                paymentData5.put("course_id", 3);
////                paymentData5.put("payment_method", "click");
////                paymentData5.put("installment", true);
////                paymentData5.put("installment_months", 3);
////
////                // Payment ma'lumotlarini bunday yarating:
////                List<Payment> payments = Arrays.asList(
////                        Payment.builder()
////                                .userId(3)
////                                .fullName("Student User 1")
////                                .paymentPhoto("payment1.jpg")
////                                .amount(new BigDecimal("1500000.00"))
////                                .paymentData("{\"course_id\": 1, \"payment_method\": \"click\", \"transaction_id\": \"txn_123456\"}")
////                                .status(PaymentStatus.SUCCESS)
////                                .build(),
////
////                        Payment.builder()
////                                .userId(4)
////                                .fullName("Instructor User 1")
////                                .paymentPhoto("payment2.jpg")
////                                .amount(new BigDecimal("500000.00"))
////                                .paymentData("{\"subscription\": \"monthly\", \"payment_method\": \"payme\", \"user_email\": \"student1@example.com\"}")
////                                .status(PaymentStatus.PENDING)
////                                .build()
////
////
////                );
////
////                paymentRepository.saveAll(payments);
////                log.info("5 ta payment yaratildi");
// //         }
//
//            // Qolgan entitylar uchun ma'lumotlar
//            initAdditionalData(lessonVideoRepository, subscribeCourseRepository, schoolUserRepository,
//                    userCarRepository, userSubscribeRepository, lVideoRepository, uploadedFileRepository,
//                    userRepository, courseRepository, lessonRepository, instructorCarRepository, subscribeRepository,
//                    schoolRepository);
//
//            log.info("Dataloader muvaffaqiyatli yakunlandi!");
//        };
//    }
//
//    private void initAdditionalData(LessonVideoRepository lessonVideoRepository,
//                                    SubscribeCourseRepository subscribeCourseRepository,
//                                    SchoolUserRepository schoolUserRepository,
//                                    UserCarRepository userCarRepository,
//                                    UserSubscribeRepository userSubscribeRepository,
//                                    LVideoRepository lVideoRepository,
//                                    UploadedFileRepository uploadedFileRepository,
//                                    UserRepository userRepository,
//                                    CourseRepository courseRepository,
//                                    LessonRepository lessonRepository,
//                                    InstructorCarRepository instructorCarRepository,
//                                    SubscribeRepository subscribeRepository,
//                                    SchoolRepository schoolRepository) {
//
//        // LessonVideo ma'lumotlari
//        if (lessonVideoRepository.count() == 0 && lessonRepository.count() > 0) {
//            List<Lesson> lessons = lessonRepository.findAll();
//            for (int i = 0; i < Math.min(5, lessons.size()); i++) {
//                LessonVideo video = LessonVideo.builder()
//                        .fullName("Video dars " + (i + 1))
//                        .title("Video " + (i + 1))
//                        .description("Bu " + (i + 1) + "-video dars")
//                        .videoUrl("https://example.com/video" + (i + 1) + ".mp4")
//                        .photoUrl("https://example.com/thumb" + (i + 1) + ".jpg")
//                        .lesson(lessons.get(i))
//                        .build();
//                lessonVideoRepository.save(video);
//            }
//            log.info("5 ta lesson video yaratildi");
//        }
//
//        // SubscribeCourse ma'lumotlari
//        if (subscribeCourseRepository.count() == 0 && subscribeRepository.count() > 0 && courseRepository.count() > 0) {
//            List<Subscribe> subscribes = subscribeRepository.findAll();
//            List<Course> courses = courseRepository.findAll();
//
//            for (int i = 0; i < Math.min(5, Math.min(subscribes.size(), courses.size())); i++) {
//                SubscribeCourse subscribeCourse = SubscribeCourse.builder()
//                        .courseId(courses.get(i).getId())
//                        .subscribeId(subscribes.get(i).getId())
//                        .build();
//                subscribeCourseRepository.save(subscribeCourse);
//            }
//            log.info("5 ta subscribe course yaratildi");
//        }
//
//        // SchoolUser ma'lumotlari
//        if (schoolUserRepository.count() == 0 && schoolRepository.count() > 0 && userRepository.count() > 0) {
//            List<School> schools = schoolRepository.findAll();
//            List<User> users = userRepository.findAll();
//
//            for (int i = 0; i < Math.min(5, Math.min(schools.size(), users.size())); i++) {
//                SchoolUser schoolUser = SchoolUser.builder()
//                        .id(new SchoolUserId(schools.get(i).getId(), users.get(i).getId()))
//                        .school(schools.get(i))
//                        .user(users.get(i))
//                        .build();
//                schoolUserRepository.save(schoolUser);
//            }
//            log.info("5 ta school user yaratildi");
//        }
//
//        // UserCar ma'lumotlari
//        if (userCarRepository.count() == 0 && userRepository.count() > 0 && instructorCarRepository.count() > 0) {
//            List<User> users = userRepository.findAll();
//            List<InstructorCar> cars = instructorCarRepository.findAll();
//
//            for (int i = 0; i < Math.min(5, Math.min(users.size(), cars.size())); i++) {
//                UserCar userCar = UserCar.builder()
//                        .userIdCarsId(users.get(i).getId())
//                        .instructorCarsId(cars.get(i).getId())
//                        .build();
//                userCarRepository.save(userCar);
//            }
//            log.info("5 ta user car yaratildi");
//        }
//
//        // UserSubscribe ma'lumotlari
//        if (userSubscribeRepository.count() == 0 && userRepository.count() > 0) {
//            List<User> users = userRepository.findAll();
//
//            for (int i = 0; i < Math.min(5, users.size()); i++) {
//                UserSubscribe userSubscribe = UserSubscribe.builder()
//                        .userId(users.get(i).getId())
//                        .subscribeCourseId(i + 1)
//                        .isSubscribed(i % 2 == 0)
//                        .build();
//                userSubscribeRepository.save(userSubscribe);
//            }
//            log.info("5 ta user subscribe yaratildi");
//        }
//
//        // LVideo ma'lumotlari
//        if (lVideoRepository.count() == 0 && lessonRepository.count() > 0 && lessonVideoRepository.count() > 0) {
//            List<Lesson> lessons = lessonRepository.findAll();
//
//            for (int i = 0; i < Math.min(5, lessons.size()); i++) {
//                LVideo lVideo = LVideo.builder()
//                        .lessonId(lessons.get(i).getId())
//                        .lessonVideoId(i + 1)
//                        .lesson(lessons.get(i))
//                        .build();
//                lVideoRepository.save(lVideo);
//            }
//            log.info("5 ta L video yaratildi");
//        }
//
//        // UploadedFile ma'lumotlari
//        if (uploadedFileRepository.count() == 0) {
//            for (int i = 1; i <= 5; i++) {
//                UploadedFile file = UploadedFile.builder()
//                        .storedFileName("file_" + i + "_stored.ext")
//                        .originalFileName("original_file_" + i + ".pdf")
//                        .contentType("application/pdf")
//                        .fileSize(1024L * i)
//                        .data(new byte[]{1, 2, 3, 4, 5})
//                        .filePath("/uploads/file_" + i)
//                        .uploadDate(LocalDateTime.now())
//                        .build();
//                uploadedFileRepository.save(file);
//            }
//            log.info("5 ta uploaded file yaratildi");
//        }
//    }
//}