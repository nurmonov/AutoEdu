package org.example.autoedu.repo;



import org.example.autoedu.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByPhoneNumber(String phoneNumber);

    Optional<User> findByEmail(String email);           // agar email bilan ham login qilmoqchi bo'lsangiz

    boolean existsByPhoneNumber(String phoneNumber);

    boolean existsByEmail(String email);
}
