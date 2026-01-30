package org.example.autoedu.repo;


import org.example.autoedu.entity.InstructorCar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstructorCarRepository extends JpaRepository<InstructorCar, Integer> {
}
