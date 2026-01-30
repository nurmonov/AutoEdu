package org.example.autoedu.repo;

import org.example.autoedu.entity.UserCar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserCarRepository extends JpaRepository<UserCar, Integer> {

    List<UserCar> findByUserIdCarsId(Integer userId);

    Optional<UserCar> findByUserIdCarsIdAndInstructorCarsId(Integer userId, Integer instructorCarId);
}
