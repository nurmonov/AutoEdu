package org.example.autoedu.repo;

import org.example.autoedu.entity.UserCar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserCarRepository extends JpaRepository<UserCar, Integer> {

    // user_id_cars_id bo'yicha qidirish (Spring Data camelCase ishlatadi)
    List<UserCar> findByUserIdCarsId(Integer userIdCarsId);

    // ikkala maydon bo'yicha aniq topish
    Optional<UserCar> findByUserIdCarsIdAndInstructorCarsId(Integer userIdCarsId, Integer instructorCarsId);

    // mavjudligini tekshirish
    boolean existsByUserIdCarsIdAndInstructorCarsId(Integer userIdCarsId, Integer instructorCarsId);

    // o'chirish uchun
    void deleteByUserIdCarsIdAndInstructorCarsId(Integer userIdCarsId, Integer instructorCarsId);
}
