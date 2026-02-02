package org.example.autoedu.service;

import lombok.RequiredArgsConstructor;
import org.example.autoedu.dto.school.SchoolResponse;
import org.example.autoedu.dto.schoolUser.SchoolUserResponse;
import org.example.autoedu.dto.user.UserResponse;
import org.example.autoedu.entity.School;
import org.example.autoedu.entity.SchoolUser;
import org.example.autoedu.entity.User;
import org.example.autoedu.entity.composite.SchoolUserId;
import org.example.autoedu.mapper.SchoolUserMapper;
import org.example.autoedu.repo.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SchoolUserService {

    private final SchoolUserRepository schoolUserRepository;
    private final SchoolRepository schoolRepository;
    private final UserRepository userRepository;
    private final SchoolUserMapper schoolUserMapper; // sizdagi mapper

    @Transactional
    public SchoolUserResponse addUserToSchool(Integer schoolId, Integer userId, String roleInSchool) {
        School school = schoolRepository.findById(schoolId)
                .orElseThrow(() -> new NoSuchElementException("Maktab topilmadi: " + schoolId));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("User topilmadi: " + userId));

        SchoolUserId id = new SchoolUserId(schoolId, userId);

        if (schoolUserRepository.existsById(id)) {
            throw new IllegalArgumentException("Bu user allaqachon shu maktabga biriktirilgan");
        }

        SchoolUser schoolUser = SchoolUser.builder()
                .id(id)
                .school(school)
                .user(user)
                // .roleInSchool(roleInSchool) // agar maydon bo'lsa qo'shing
                .build();

        SchoolUser saved = schoolUserRepository.save(schoolUser);
        return toFullResponse(saved);
    }

    @Transactional(readOnly = true)
    public List<SchoolUserResponse> getUsersBySchool(Integer schoolId) {
        return schoolUserRepository.findBySchoolId(schoolId).stream()
                .map(this::toFullResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<SchoolUserResponse> getSchoolsByUser(Integer userId) {
        return schoolUserRepository.findByUserId(userId).stream()
                .map(this::toFullResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public void removeUserFromSchool(Integer schoolId, Integer userId) {
        SchoolUserId id = new SchoolUserId(schoolId, userId);

        if (!schoolUserRepository.existsById(id)) {
            throw new NoSuchElementException("Bog‘lanish topilmadi");
        }

        schoolUserRepository.deleteById(id);
    }

    // Mapperdan foydalanib + to'liq obyekt qo'shish
    private SchoolUserResponse toFullResponse(SchoolUser entity) {
        // Mapperdan oddiy response olish (sizdagi mapperdan foydalanamiz)
        SchoolUserResponse base = schoolUserMapper.toResponse(entity);

        // School ma'lumotlarini qo'shamiz
        School school = schoolRepository.findById(entity.getId().getSchoolId())
                .orElseThrow(() -> new NoSuchElementException("Maktab topilmadi"));

        SchoolResponse schoolDto = SchoolResponse.builder()
                .id(school.getId())
                .fullName(school.getFullName())
                .status(String.valueOf(school.getStatus()))
                .phoneNumber(school.getPhoneNumber())
                .logo(school.getLogo())
                .location(school.getLocation())
                .build();

        // User ma'lumotlarini qo'shamiz
        User user = userRepository.findById(entity.getId().getUserId())
                .orElseThrow(() -> new NoSuchElementException("User topilmadi"));

        UserResponse userDto = UserResponse.builder()
                .id(user.getId())
                .fullName(user.getFullName())
                .phoneNumber(user.getPhoneNumber())
                .email(user.getEmail())
                .role(user.getRole() != null ? user.getRole().name() : null)
                .photo(user.getPhoto())
                .jshshr(user.getJshshr())
                .seriaRaqam(user.getSeriaRaqam())
                .build();

        // Hammasini birlashtirib qaytarish
        return SchoolUserResponse.builder()
                .schoolId(base.getSchoolId())
                .school(schoolDto)
                .userId(base.getUserId())
                .user(userDto)
                // .roleInSchool(base.getRoleInSchool()) // agar role maydoni bo'lsa
                .build();
    }
}
