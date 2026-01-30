package org.example.autoedu.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.autoedu.dto.schoolUser.SchoolUserResponse;
import org.example.autoedu.entity.School;
import org.example.autoedu.entity.SchoolUser;
import org.example.autoedu.entity.User;
import org.example.autoedu.entity.composite.SchoolUserId;
import org.example.autoedu.repo.SchoolRepository;
import org.example.autoedu.repo.SchoolUserRepository;
import org.example.autoedu.repo.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
@Service
@RequiredArgsConstructor
public class SchoolUserService {

    private final SchoolUserRepository schoolUserRepository;
    private final SchoolRepository schoolRepository;
    private final UserRepository userRepository;

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
                // .roleInSchool(roleInSchool)
                .build();

        schoolUserRepository.save(schoolUser);

        return toResponse(schoolUser);
    }

    @Transactional
    public List<SchoolUserResponse> getUsersBySchool(Integer schoolId) {
        return schoolUserRepository.findBySchool_Id(schoolId)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Transactional
    public List<SchoolUserResponse> getSchoolsByUser(Integer userId) {
        return schoolUserRepository.findByUser_Id(userId)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Transactional
    public void removeUserFromSchool(Integer schoolId, Integer userId) {
        SchoolUserId id = new SchoolUserId(schoolId, userId);

        if (!schoolUserRepository.existsById(id)) {
            throw new NoSuchElementException("Bog‘lanish topilmadi");
        }

        schoolUserRepository.deleteById(id);
    }

    private SchoolUserResponse toResponse(SchoolUser entity) {
        return SchoolUserResponse.builder()
                .schoolId(entity.getSchool().getId())
                .userId(entity.getUser().getId())
                // .roleInSchool(entity.getRoleInSchool())
                .build();
    }
}

