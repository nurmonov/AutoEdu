package org.example.autoedu.repo;

import org.example.autoedu.entity.School;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SchoolRepository extends JpaRepository<School, Integer> {


    Optional<School> findByFullNameIgnoreCase(String fullName);

    List<School> findByStatus(String status);

    boolean existsByPhoneNumber(String phoneNumber);
}
