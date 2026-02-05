package org.example.autoedu.repo;



import org.example.autoedu.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByPhoneNumber(String phoneNumber);

    Optional<User> findByEmail(String email);          

    boolean existsByPhoneNumber(String phoneNumber);

    boolean existsByEmail(String email);

//    @Query("SELECT u FROM User u WHERE u.email = :value OR u.fullName = :value")
//    Optional<User> findByEmailOrFullName(@Param("value") String value);
}
