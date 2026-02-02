package org.example.autoedu.service;

import lombok.RequiredArgsConstructor;
import org.example.autoedu.dto.instructorCar.InstructorCarResponse;
import org.example.autoedu.dto.user.UserResponse;
import org.example.autoedu.dto.userCar.UserCarAddRequest;
import org.example.autoedu.dto.userCar.UserCarFullResponse;
import org.example.autoedu.dto.userCar.UserCarResponse;
import org.example.autoedu.entity.InstructorCar;
import org.example.autoedu.entity.User;
import org.example.autoedu.entity.UserCar;
import org.example.autoedu.mapper.UserCarMapper;
import org.example.autoedu.repo.InstructorCarRepository;
import org.example.autoedu.repo.UserCarRepository;
import org.example.autoedu.repo.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class UserCarService {

    private final UserCarRepository userCarRepository;
    private final UserRepository userRepository;
    private final InstructorCarRepository instructorCarRepository;
    private final UserCarMapper userCarMapper; // sizdagi mapper (agar bor bo'lsa)

    @Transactional(readOnly = true)
    public List<UserCarFullResponse> getUserCars(Integer userId) {
        return userCarRepository.findByUserIdCarsId(userId).stream()
                .map(this::toFullResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public UserCarFullResponse addCarToUser(UserCarAddRequest request) {
        // Mavjudligini tekshirish
        if (userCarRepository.existsByUserIdCarsIdAndInstructorCarsId(
                request.getUserId(), request.getInstructorCarId())) {
            throw new IllegalArgumentException("Bu user allaqachon shu mashinaga bog‘langan");
        }

        UserCar uc = UserCar.builder()
                .userIdCarsId(request.getUserId())
                .instructorCarsId(request.getInstructorCarId())
                .build();

        UserCar saved = userCarRepository.save(uc);

        // To'liq obyekt qaytarish
        return toFullResponse(saved);
    }

    @Transactional
    public void removeCarFromUser(Integer userId, Integer instructorCarId) {
        if (!userCarRepository.existsByUserIdCarsIdAndInstructorCarsId(userId, instructorCarId)) {
            throw new NoSuchElementException("Bog‘lanish topilmadi");
        }

        userCarRepository.deleteByUserIdCarsIdAndInstructorCarsId(userId, instructorCarId);
    }

    // Mapperdan foydalanib + to'liq obyekt qo'shish
    private UserCarFullResponse toFullResponse(UserCar entity) {
        // Agar mapper bor bo'lsa — oddiy mapping
        UserCarResponse base = userCarMapper.toResponse(entity); // sizdagi mapper

        // User ma'lumotlarini olish
        User user = userRepository.findById(entity.getUserIdCarsId())
                .orElseThrow(() -> new NoSuchElementException("User topilmadi"));

        UserResponse userDto = UserResponse.builder()
                .id(user.getId())
                .fullName(user.getFullName())
                .phoneNumber(user.getPhoneNumber())
                .email(user.getEmail())
                .role(user.getRole() != null ? user.getRole().name() : null)
                .photo(user.getPhoto())
                .build();

        // Instruktor mashinasi ma'lumotlarini olish
        InstructorCar car = instructorCarRepository.findById(entity.getInstructorCarsId())
                .orElseThrow(() -> new NoSuchElementException("Mashina topilmadi"));

        InstructorCarResponse carDto = InstructorCarResponse.builder()
                .id(car.getId())
                .fullName(car.getFullName())
                .photo(car.getPhoto())
                .number(car.getNumber())
                .instructorId(car.getInstruktorId())
                .build();

        // Hammasini birlashtirib qaytarish
        return UserCarFullResponse.builder()
                .id(base.getId())
                .user(userDto)
                .instructorCar(carDto)
                .build();
    }
}