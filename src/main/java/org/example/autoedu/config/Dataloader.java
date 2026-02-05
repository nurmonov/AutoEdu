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
//import java.util.List;
//import java.util.UUID;
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
//            PaymentRepository paymentRepository,
//            SchoolRepository schoolRepository,
//            SchoolUserRepository schoolUserRepository,
//            UserCarRepository userCarRepository,
//            UserSubscribeRepository userSubscribeRepository,
//           // LVideoRepository lVideoRepository,
//            UploadedFileRepository uploadedFileRepository) {
//
//        return args -> {
//            log.info("Dataloader ishga tushmoqda...");
//
//            // 1. User ma'lumotlari (5 ta + 1 ta Super Admin)
//            if (userRepository.count() == 0) {
//                User superAdmin = User.builder()
//                        .fullName("Super Administrator")
//                        .phoneNumber("+998990000001")  // oddiy login uchun
//                        .email("superadmin@example.com")
//                        .password(passwordEncoder.encode("super123"))
//                        .photo("superadmin.jpg")
//                        .jshshr("00000000000000")
//                        .seriaRaqam("AA0000000")
//                        .passportPhoto("passport_super.jpg")
//                        .role(Role.SUPER_ADMIN)
//                        .status(Status.ACTIVE)
//                        .build();
//
//                List<User> users = Arrays.asList(
//                        superAdmin,
//                        User.builder().fullName("Admin One").phoneNumber("+998901234568").email("admin1@example.com")
//                                .password(passwordEncoder.encode("admin123")).role(Role.ADMIN).status(Status.ACTIVE).build(),
//                        User.builder().fullName("O'qituvchi 1").phoneNumber("+998901234570").email("teacher1@example.com")
//                                .password(passwordEncoder.encode("teacher123")).priceHour(60000).freeHours(5).role(Role.INSTRUCTOR).status(Status.ACTIVE).build(),
//                        User.builder().fullName("O'qituvchi 2").phoneNumber("+998901234571").email("teacher2@example.com")
//                                .password(passwordEncoder.encode("teacher123")).priceHour(55000).freeHours(4).role(Role.INSTRUCTOR).status(Status.ACTIVE).build(),
//                        User.builder().fullName("Talaba 1").phoneNumber("+998901234569").email("student1@example.com")
//                                .password(passwordEncoder.encode("student123")).role(Role.STUDENT).status(Status.ACTIVE).build(),
//                        User.builder().fullName("Talaba 2").phoneNumber("+998901234572").email("student2@example.com")
//                                .password(passwordEncoder.encode("student123")).role(Role.STUDENT).status(Status.ACTIVE).build()
//                );
//
//                userRepository.saveAll(users);
//                log.info("6 ta user yaratildi (1 ta Super Admin, 1 ta Admin, 2 ta O'qituvchi, 2 ta Talaba)");
//            }
//
//            // 2. School ma'lumotlari (5 ta)
//            if (schoolRepository.count() == 0) {
//                List<School> schools = Arrays.asList(
//                        School.builder().fullName("Avto Maktab Yunusobod").phoneNumber("+998711234567").logo("yunusobod.jpg").location("Toshkent, Yunusobod").status(Status.ACTIVE).build(),
//                        School.builder().fullName("Avto Maktab Chilonzor").phoneNumber("+998711234568").logo("chilonzor.jpg").location("Toshkent, Chilonzor").status(Status.ACTIVE).build(),
//                        School.builder().fullName("Avto Maktab Sergeli").phoneNumber("+998711234569").logo("sergeli.jpg").location("Toshkent, Sergeli").status(Status.ACTIVE).build(),
//                        School.builder().fullName("Avto Maktab Mirzo Ulug'bek").phoneNumber("+998711234570").logo("mirzo.jpg").location("Toshkent, Mirzo Ulug'bek").status(Status.ACTIVE).build(),
//                        School.builder().fullName("Avto Maktab Yashnobod").phoneNumber("+998711234571").logo("yashnobod.jpg").location("Toshkent, Yashnobod").status(Status.ACTIVE).build()
//                );
//                schoolRepository.saveAll(schools);
//                log.info("5 ta school yaratildi");
//            }
//
//            // 3. Course ma'lumotlari (5 ta)
//            if (courseRepository.count() == 0) {
//                List<Course> courses = Arrays.asList(
//                        Course.builder().fullName("Boshlang'ich Haydash").title("Kurs 1").description("Asosiy qoidalar").price(new BigDecimal("1500000")).photo("course1.jpg").videoCount(12).logo("logo1.png").location("Toshkent").build(),
//                        Course.builder().fullName("Ilg'or Haydash").title("Kurs 2").description("Qiyin sharoitlar").price(new BigDecimal("2200000")).photo("course2.jpg").videoCount(18).logo("logo2.png").location("Toshkent").build(),
//                        Course.builder().fullName("Yuk Mashinalari").title("Kurs 3").description("C kategoriya").price(new BigDecimal("2800000")).photo("course3.jpg").videoCount(20).logo("logo3.png").location("Toshkent").build(),
//                        Course.builder().fullName("Avtobus Haydash").title("Kurs 4").description("D kategoriya").price(new BigDecimal("3200000")).photo("course4.jpg").videoCount(25).logo("logo4.png").location("Toshkent").build(),
//                        Course.builder().fullName("Tezkor Kurs").title("Kurs 5").description("Qisqa muddatli").price(new BigDecimal("1200000")).photo("course5.jpg").videoCount(10).logo("logo5.png").location("Toshkent").build()
//                );
//                courseRepository.saveAll(courses);
//                log.info("5 ta course yaratildi");
//            }
//
//            // 4. Lesson ma'lumotlari (5 ta, kurslarga bog'langan)
//            if (lessonRepository.count() == 0) {
//                List<Course> courses = courseRepository.findAll();
//                if (courses.size() >= 2) {
//                    List<Lesson> lessons = Arrays.asList(
//                            Lesson.builder().fullName("Dars 1").title("Yo'l qoidalari").description("Asosiy belgilarni o'rganish").file("rules.pdf").course(courses.get(0)).build(),
//                            Lesson.builder().fullName("Dars 2").title("Mashina tuzilishi").description("Dvigatel va transmissiya").file("structure.pdf").course(courses.get(0)).build(),
//                            Lesson.builder().fullName("Dars 3").title("Qishki haydash").description("Qor va muzda harakat").file("winter.pdf").course(courses.get(1)).build(),
//                            Lesson.builder().fullName("Dars 4").title("Favqulodda holatlar").description("Tormoz va manevr").file("emergency.pdf").course(courses.get(1)).build(),
//                            Lesson.builder().fullName("Dars 5").title("Yuk mashinalari").description("C kategoriya asoslari").file("truck.pdf").course(courses.get(2)).build()
//                    );
//                    lessonRepository.saveAll(lessons);
//                    log.info("5 ta lesson yaratildi va kurslarga bog'landi");
//                }
//            }
//
//            // 5. UploadedFile ma'lumotlari (5 ta, faqat lesson ga bog'langan)
//            if (uploadedFileRepository.count() == 0) {
//                List<Lesson> lessons = lessonRepository.findAll();
//                if (!lessons.isEmpty()) {
//                    List<UploadedFile> files = Arrays.asList(
//                            UploadedFile.builder().originalFileName("Qoidalar.pdf").storedFileName(UUID.randomUUID() + ".pdf").contentType("application/pdf").fileSize(1024000L).filePath("/uploads/rules.pdf").uploadDate(LocalDateTime.now()).lesson(lessons.get(0)).build(),
//                            UploadedFile.builder().originalFileName("Dvigatel.pdf").storedFileName(UUID.randomUUID() + ".pdf").contentType("application/pdf").fileSize(2048000L).filePath("/uploads/engine.pdf").uploadDate(LocalDateTime.now()).lesson(lessons.get(1)).build(),
//                            UploadedFile.builder().originalFileName("Qishki.pdf").storedFileName(UUID.randomUUID() + ".pdf").contentType("application/pdf").fileSize(1536000L).filePath("/uploads/winter.pdf").uploadDate(LocalDateTime.now()).lesson(lessons.get(2)).build(),
//                            UploadedFile.builder().originalFileName("Favqulodda.pdf").storedFileName(UUID.randomUUID() + ".pdf").contentType("application/pdf").fileSize(1280000L).filePath("/uploads/emergency.pdf").uploadDate(LocalDateTime.now()).lesson(lessons.get(3)).build(),
//                            UploadedFile.builder().originalFileName("Yuk.pdf").storedFileName(UUID.randomUUID() + ".pdf").contentType("application/pdf").fileSize(2560000L).filePath("/uploads/truck.pdf").uploadDate(LocalDateTime.now()).lesson(lessons.get(4)).build()
//                    );
//                    uploadedFileRepository.saveAll(files);
//                    log.info("5 ta uploaded file yaratildi va darslarga bog'landi");
//                }
//            }
//
//            // 6. InstructorCar (5 ta)
//            if (instructorCarRepository.count() == 0) {
//                List<InstructorCar> cars = Arrays.asList(
//                        InstructorCar.builder().fullName("Chevrolet Cobalt").photo("cobalt.jpg").number("01A777AA").instruktorId(3).build(),
//                        InstructorCar.builder().fullName("Nissan Sunny").photo("sunny.jpg").number("01B888BB").instruktorId(3).build(),
//                        InstructorCar.builder().fullName("Toyota Camry").photo("camry.jpg").number("01C999CC").instruktorId(4).build(),
//                        InstructorCar.builder().fullName("Hyundai Accent").photo("accent.jpg").number("01D111DD").instruktorId(4).build(),
//                        InstructorCar.builder().fullName("KIA Rio").photo("rio.jpg").number("01E222EE").instruktorId(3).build()
//                );
//                instructorCarRepository.saveAll(cars);
//                log.info("5 ta instructor mashinasi yaratildi");
//            }
//
//            // 7. Subscribe (5 ta)
//            if (subscribeRepository.count() == 0) {
//                List<Subscribe> subs = Arrays.asList(
//                        Subscribe.builder().fullName("Haftalik").price(new BigDecimal("150000")).additionals("Asosiy kurslar").subscribeData(SubscribeData.WEEKLY).sale("5%").promo("WEEK5").promoId(1).build(),
//                        Subscribe.builder().fullName("Oylik").price(new BigDecimal("500000")).additionals("Barcha kurslar").subscribeData(SubscribeData.MONTHLY).sale("10%").promo("MONTH10").promoId(2).build(),
//                        Subscribe.builder().fullName("Kvartal").price(new BigDecimal("1400000")).additionals("Testlar + video").subscribeData(SubscribeData.QUARTERLY).sale("15%").promo("QRT15").promoId(3).build(),
//                        Subscribe.builder().fullName("Yarim yillik").price(new BigDecimal("2500000")).additionals("Shaxsiy murabbiy").subscribeData(SubscribeData.HALF_YEARLY).sale("18%").promo("HALF18").promoId(4).build(),
//                        Subscribe.builder().fullName("Yillik").price(new BigDecimal("4500000")).additionals("Cheksiz kirish").subscribeData(SubscribeData.YEARLY).sale("25%").promo("YEAR25").promoId(5).build()
//                );
//                subscribeRepository.saveAll(subs);
//                log.info("5 ta obuna yaratildi");
//            }
//
//            // 8. SubscribeCourse (5 ta)
//            if (subscribeCourseRepository.count() == 0) {
//                List<Subscribe> subs = subscribeRepository.findAll();
//                List<Course> courses = courseRepository.findAll();
//                if (subs.size() >= 5 && courses.size() >= 2) {
//                    List<SubscribeCourse> scList = Arrays.asList(
//                            SubscribeCourse.builder().courseId(courses.get(0).getId()).subscribeId(subs.get(0).getId()).build(),
//                            SubscribeCourse.builder().courseId(courses.get(0).getId()).subscribeId(subs.get(1).getId()).build(),
//                            SubscribeCourse.builder().courseId(courses.get(1).getId()).subscribeId(subs.get(2).getId()).build(),
//                            SubscribeCourse.builder().courseId(courses.get(1).getId()).subscribeId(subs.get(3).getId()).build(),
//                            SubscribeCourse.builder().courseId(courses.get(0).getId()).subscribeId(subs.get(4).getId()).build()
//                    );
//                    subscribeCourseRepository.saveAll(scList);
//                    log.info("5 ta subscribe-course bog'lanishi yaratildi");
//                }
//            }
//
//            // 9. Payment (5 ta)
//            // Payment ma'lumotlari (5 ta test to'lovi)
//            if (paymentRepository.count() == 0) {
//                List<User> users = userRepository.findAll();
//
//                // Xavfsizlik tekshiruvi: kamida 5 ta user bo'lishi kerak (index 0..4 ishlatiladi)
//                if (users.size() < 5) {
//                    log.warn("Payment yaratish uchun yetarli user yo'q! Kamida 5 ta user bo'lishi kerak. Hozir: {}", users.size());
//                } else {
//                    List<Payment> payments = Arrays.asList(
//                            Payment.builder()
//                                    .userId(users.get(0).getId())  // Super Admin yoki birinchi user
//                                    .fullName(users.get(0).getFullName())
//                                    .paymentPhoto("receipt_click_001.jpg")
//                                    .amount(new BigDecimal("1500000.00"))
//                                    .paymentData("{\"method\":\"click\", \"transaction_id\":\"TXN-001\", \"status\":\"success\"}")
//                                    .status(PaymentStatus.SUCCESS)
//                                    .build(),
//
//                            Payment.builder()
//                                    .userId(users.get(1).getId())  // Admin
//                                    .fullName(users.get(1).getFullName())
//                                    .paymentPhoto("receipt_payme_002.jpg")
//                                    .amount(new BigDecimal("500000.00"))
//                                    .paymentData("{\"method\":\"payme\", \"transaction_id\":\"TXN-002\", \"status\":\"pending\"}")
//                                    .status(PaymentStatus.PENDING)
//                                    .build(),
//
//                            Payment.builder()
//                                    .userId(users.get(2).getId())  // O'qituvchi 1
//                                    .fullName(users.get(2).getFullName())
//                                    .paymentPhoto("receipt_card_003.jpg")
//                                    .amount(new BigDecimal("2200000.00"))
//                                    .paymentData("{\"method\":\"card\", \"last4\":\"4242\", \"status\":\"success\"}")
//                                    .status(PaymentStatus.SUCCESS)
//                                    .build(),
//
//                            Payment.builder()
//                                    .userId(users.get(3).getId())  // O'qituvchi 2
//                                    .fullName(users.get(3).getFullName())
//                                    .paymentPhoto("receipt_cash_004.jpg")
//                                    .amount(new BigDecimal("1400000.00"))
//                                    .paymentData("{\"method\":\"cash\", \"branch\":\"Yunusobod\", \"status\":\"success\"}")
//                                    .status(PaymentStatus.SUCCESS)
//                                    .build(),
//
//                            Payment.builder()
//                                    .userId(users.get(4).getId())  // Talaba 1
//                                    .fullName(users.get(4).getFullName())
//                                    .paymentPhoto("receipt_yearly_005.jpg")
//                                    .amount(new BigDecimal("4500000.00"))
//                                    .paymentData("{\"method\":\"click\", \"promo\":\"YEAR25\", \"status\":\"success\"}")
//                                    .status(PaymentStatus.SUCCESS)
//                                    .build()
//                    );
//
//                    paymentRepository.saveAll(payments);
//                    log.info("5 ta test payment muvaffaqiyatli yaratildi");
//                }
//            }
//
//            // 10. SchoolUser (5 ta)
//            if (schoolUserRepository.count() == 0) {
//                List<School> schools = schoolRepository.findAll();
//                List<User> users = userRepository.findAll();
//                if (schools.size() >= 5 && users.size() >= 5) {
//                    List<SchoolUser> suList = Arrays.asList(
//                            SchoolUser.builder().id(new SchoolUserId(schools.get(0).getId(), users.get(0).getId())).school(schools.get(0)).user(users.get(0)).build(),
//                            SchoolUser.builder().id(new SchoolUserId(schools.get(1).getId(), users.get(1).getId())).school(schools.get(1)).user(users.get(1)).build(),
//                            SchoolUser.builder().id(new SchoolUserId(schools.get(2).getId(), users.get(2).getId())).school(schools.get(2)).user(users.get(2)).build(),
//                            SchoolUser.builder().id(new SchoolUserId(schools.get(3).getId(), users.get(3).getId())).school(schools.get(3)).user(users.get(3)).build(),
//                            SchoolUser.builder().id(new SchoolUserId(schools.get(4).getId(), users.get(4).getId())).school(schools.get(4)).user(users.get(4)).build()
//                    );
//                    schoolUserRepository.saveAll(suList);
//                    log.info("5 ta school-user bog'lanishi yaratildi");
//                }
//            }
//
//            // 11. UserCar (5 ta)
//            if (userCarRepository.count() == 0) {
//                List<User> users = userRepository.findAll();
//                List<InstructorCar> cars = instructorCarRepository.findAll();
//                if (users.size() >= 5 && cars.size() >= 5) {
//                    List<UserCar> ucList = Arrays.asList(
//                            UserCar.builder().userIdCarsId(users.get(2).getId()).instructorCarsId(cars.get(0).getId()).build(),
//                            UserCar.builder().userIdCarsId(users.get(2).getId()).instructorCarsId(cars.get(1).getId()).build(),
//                            UserCar.builder().userIdCarsId(users.get(3).getId()).instructorCarsId(cars.get(2).getId()).build(),
//                            UserCar.builder().userIdCarsId(users.get(3).getId()).instructorCarsId(cars.get(3).getId()).build(),
//                            UserCar.builder().userIdCarsId(users.get(2).getId()).instructorCarsId(cars.get(4).getId()).build()
//                    );
//                    userCarRepository.saveAll(ucList);
//                    log.info("5 ta user-car bog'lanishi yaratildi");
//                }
//            }
//
//            // 12. UserSubscribe (5 ta)
//            if (userSubscribeRepository.count() == 0) {
//                List<User> users = userRepository.findAll();
//                if (users.size() >= 5) {
//                    List<UserSubscribe> usList = Arrays.asList(
//                            UserSubscribe.builder().userId(users.get(3).getId()).subscribeCourseId(1).isSubscribed(true).build(),
//                            UserSubscribe.builder().userId(users.get(3).getId()).subscribeCourseId(2).isSubscribed(true).build(),
//                            UserSubscribe.builder().userId(users.get(4).getId()).subscribeCourseId(3).isSubscribed(false).build(),
//                            UserSubscribe.builder().userId(users.get(4).getId()).subscribeCourseId(4).isSubscribed(true).build(),
//                            UserSubscribe.builder().userId(users.get(2).getId()).subscribeCourseId(5).isSubscribed(false).build()
//                    );
//                    userSubscribeRepository.saveAll(usList);
//                    log.info("5 ta user-subscribe yaratildi");
//                }
//            }
//
//            // 13. LessonVideo (5 ta)
//            if (lessonVideoRepository.count() == 0) {
//                List<Lesson> lessons = lessonRepository.findAll();
//                if (!lessons.isEmpty()) {
//                    List<LessonVideo> videos = Arrays.asList(
//                            LessonVideo.builder().fullName("Video 1").title("Yo'l belgilari video").description("Belgilar tushuntirilishi").videoUrl("https://example.com/v1.mp4").photoUrl("https://example.com/thumb1.jpg").lesson(lessons.get(0)).build(),
//                            LessonVideo.builder().fullName("Video 2").title("Dvigatel video").description("Dvigatel ishlashi").videoUrl("https://example.com/v2.mp4").photoUrl("https://example.com/thumb2.jpg").lesson(lessons.get(1)).build(),
//                            LessonVideo.builder().fullName("Video 3").title("Qishki haydash").description("Qor sharoitida").videoUrl("https://example.com/v3.mp4").photoUrl("https://example.com/thumb3.jpg").lesson(lessons.get(2)).build(),
//                            LessonVideo.builder().fullName("Video 4").title("Favqulodda").description("Tormozlash").videoUrl("https://example.com/v4.mp4").photoUrl("https://example.com/thumb4.jpg").lesson(lessons.get(3)).build(),
//                            LessonVideo.builder().fullName("Video 5").title("Yuk mashinasi").description("C kategoriya").videoUrl("https://example.com/v5.mp4").photoUrl("https://example.com/thumb5.jpg").lesson(lessons.get(4)).build()
//                    );
//                    lessonVideoRepository.saveAll(videos);
//                    log.info("5 ta lesson video yaratildi");
//                }
//            }
//
//            // 14. LVideo (5 ta) - bog'lanish
////            if (lVideoRepository.count() == 0) {
////                List<Lesson> lessons = lessonRepository.findAll();
////                List<LessonVideo> videos = lessonVideoRepository.findAll();
////                if (lessons.size() >= 5 && videos.size() >= 5) {
////                    List<LVideo> lvList = Arrays.asList(
////                            LVideo.builder().lessonId(lessons.get(0).getId()).lessonVideoId(videos.get(0).getId()).lesson(lessons.get(0)).build(),
////                            LVideo.builder().lessonId(lessons.get(1).getId()).lessonVideoId(videos.get(1).getId()).lesson(lessons.get(1)).build(),
////                            LVideo.builder().lessonId(lessons.get(2).getId()).lessonVideoId(videos.get(2).getId()).lesson(lessons.get(2)).build(),
////                            LVideo.builder().lessonId(lessons.get(3).getId()).lessonVideoId(videos.get(3).getId()).lesson(lessons.get(3)).build(),
////                            LVideo.builder().lessonId(lessons.get(4).getId()).lessonVideoId(videos.get(4).getId()).lesson(lessons.get(4)).build()
////                    );
////                    lVideoRepository.saveAll(lvList);
////                    log.info("5 ta LVideo bog'lanishi yaratildi");
////                }
////            }
//
//            log.info("Dataloader to'liq muvaffaqiyatli yakunlandi!");
//        };
//    }
//}