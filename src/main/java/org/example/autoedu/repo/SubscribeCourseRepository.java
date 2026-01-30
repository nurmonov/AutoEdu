package org.example.autoedu.repo;

import org.example.autoedu.entity.SubscribeCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubscribeCourseRepository extends JpaRepository<SubscribeCourse, Integer> {

    List<SubscribeCourse> findBySubscribeId(Integer subscribeId);

    List<SubscribeCourse> findByCourseId(Integer courseId);
}
