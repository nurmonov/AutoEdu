package org.example.autoedu.repo;

import org.example.autoedu.entity.SchoolUser;
import org.example.autoedu.entity.composite.SchoolUserId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SchoolUserRepository extends JpaRepository<SchoolUser, SchoolUserId> {

    // Maktab bo'yicha barcha userlarni olish
    List<SchoolUser> findBySchoolId(Integer schoolId);

    // User bo'yicha barcha maktablarni olish
    List<SchoolUser> findByUserId(Integer userId);

    // Bitta bog'lanishni aniq topish
    Optional<SchoolUser> findBySchoolIdAndUserId(Integer schoolId, Integer userId);

    // Bog'lanish mavjudligini tekshirish
    boolean existsBySchoolIdAndUserId(Integer schoolId, Integer userId);

    // Maktabdan userlarni o'chirish
    void deleteBySchoolId(Integer schoolId);

    // Userdan maktablarni o'chirish
    void deleteByUserId(Integer userId);

    // Agar role bo'yicha filtr kerak bo'lsa (masalan faqat instruktorlar)
    // List<SchoolUser> findBySchoolIdAndRoleInSchool(Integer schoolId, String roleInSchool);
}