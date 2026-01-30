package org.example.autoedu.repo;

import org.example.autoedu.entity.SchoolUser;
import org.example.autoedu.entity.composite.SchoolUserId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SchoolUserRepository extends JpaRepository<SchoolUser, SchoolUserId> {


    List<SchoolUser> findBySchool_Id(Integer schoolId);

    List<SchoolUser> findByUser_Id(Integer userId);

    Optional<SchoolUser> findBySchoolIdAndUserId(Integer schoolId, Integer userId);

    void deleteBySchoolIdAndUserId(Integer schoolId, Integer userId);
}