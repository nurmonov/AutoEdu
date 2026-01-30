package org.example.autoedu.repo;

import org.example.autoedu.entity.UserSubscribe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;



@Repository
public interface UserSubscribeRepository extends JpaRepository<UserSubscribe, Integer> {

    List<UserSubscribe> findByUserId(Integer userId);

    Optional<UserSubscribe> findByUserIdAndSubscribeCourseId(Integer userId, Integer subscribeCourseId);

    void deleteByUserIdAndSubscribeCourseId(Integer userId, Integer subscribeCourseId);
}
