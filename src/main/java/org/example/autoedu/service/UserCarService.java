//package org.example.autoedu.service;
//
//
//import lombok.RequiredArgsConstructor;
//import org.example.autoedu.dto.useerCar.UserCarAddRequest;
//import org.example.autoedu.dto.useerCar.UserCarResponse;
//import org.example.autoedu.entity.UserCar;
//import org.example.autoedu.repo.UserCarRepository;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//import java.util.NoSuchElementException;
//import java.util.stream.Collectors;
//
//
//
//@Service
//@RequiredArgsConstructor
//public class UserCarService {
//
//    private final UserCarRepository userCarRepository;
//
//    @Transactional(readOnly = true)
//    public List<UserCarResponse> getUserCars(Integer userId) {
//        return userCarRepository.findByUserIdCarsId(userId).stream()
//                .map(this::toResponse)
//                .collect(Collectors.toList());
//    }
//
//    @Transactional
//    public UserCarResponse addCarToUser(UserCarAddRequest request) {
//        // Mavjudligini tekshirish
//        if (userCarRepository.findByUserIdCarsIdAndInstructorCarsId(
//                request.getUserId(), request.getInstructorCarId()).isPresent()) {
//            throw new IllegalArgumentException("Bu user allaqachon shu mashinaga bog‘langan");
//        }
//
//        UserCar uc = UserCar.builder()
//                .userIdCarsId(request.getUserId())
//                .instructorCarsId(request.getInstructorCarId())
//                .build();
//
//        UserCar saved = userCarRepository.save(uc);
//        return toResponse(saved);
//    }
//
//    @Transactional
//    public void removeCarFromUser(Integer userId, Integer instructorCarId) {
//        UserCar uc = userCarRepository.findByUserIdCarsIdAndInstructorCarsId(userId, instructorCarId)
//                .orElseThrow(() -> new NoSuchElementException("Bog‘lanish topilmadi"));
//
//        userCarRepository.delete(uc);
//    }
//
//    private UserCarResponse toResponse(UserCar entity) {
//        return UserCarResponse.builder()
//                .id(entity.getId())
//                .userId(entity.getUserIdCarsId())
//                .instructorCarId(entity.getInstructorCarsId())
//                .build();
//    }
//}