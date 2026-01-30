//package org.example.autoedu.config;
//
//
//
//import lombok.RequiredArgsConstructor;
//import org.example.autoedu.entity.enums.Role;
//import org.example.autoedu.entity.User;
//import org.example.autoedu.entity.enums.Status;
//import org.example.autoedu.repo.UserRepository;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.env.Environment;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//
//@Configuration
//@RequiredArgsConstructor
//public class DataLoader {
//
//    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;
//    private final Environment environment;
//
//    @Bean
//    public CommandLineRunner loadSuperAdmin() {
//        return args -> {
//            // Faqat "dev" yoki "local" profilida ishlasin (productionda ishlamasin)
//            String activeProfile = environment.getActiveProfiles().length > 0
//                    ? environment.getActiveProfiles()[0]
//                    : "default";
//
//            if (!"dev".equals(activeProfile) && !"local".equals(activeProfile)) {
//                System.out.println("DataLoader faqat dev/local profilida ishlaydi. Current: " + activeProfile);
//                return;
//            }
//
//            String superAdminPhone = "+998991234567";   // o'zingiz o'zgartiring
//            String superAdminPassword = "admin123";     // oddiy string, hash qilinadi
//
//            if (!userRepository.existsByPhoneNumber(superAdminPhone)) {
//                User superAdmin = User.builder()
//                        .fullName("Super Admin Zubayr")
//                        .phoneNumber(superAdminPhone)
//                        .email("superadmin@autoedu.uz")
//                        .password(passwordEncoder.encode(superAdminPassword))  // hash qilamiz
//                        .role(Role.ADMIN)
//
//                        .jshshr("12345678901234")  // test uchun
//                        .seriaRaqam("AB1234567")
//                        .build();
//
//                userRepository.save(superAdmin);
//
//                System.out.println("=====================================");
//                System.out.println("SUPER ADMIN yaratildi!");
//                System.out.println("Telefon:     " + superAdminPhone);
//                System.out.println("Parol:       " + superAdminPassword);
//                System.out.println("Role:        SUPER_ADMIN");
//                System.out.println("Status:      ACTIVE");
//                System.out.println("Login: /api/auth/login da sinab ko'ring");
//                System.out.println("=====================================");
//            } else {
//                System.out.println("SuperAdmin allaqachon mavjud, qayta yaratilmadi.");
//            }
//        };
//    }
//}