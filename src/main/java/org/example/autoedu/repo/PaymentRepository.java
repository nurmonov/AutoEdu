package org.example.autoedu.repo;



import org.example.autoedu.entity.Payment;
import org.example.autoedu.entity.enums.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {

    List<Payment> findByUserId(Integer userId);

    List<Payment> findByStatus(PaymentStatus status);
}
